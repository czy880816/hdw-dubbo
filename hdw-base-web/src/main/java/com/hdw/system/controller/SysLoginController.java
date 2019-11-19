package com.hdw.system.controller;


import cn.hutool.core.codec.Base64;
import cn.hutool.crypto.Mode;
import cn.hutool.crypto.Padding;
import cn.hutool.crypto.symmetric.AES;
import com.hdw.common.base.entity.LoginUser;
import com.hdw.common.config.redis.IRedisService;
import com.hdw.common.constants.CommonConstants;
import com.hdw.common.result.CommonResult;
import com.hdw.enterprise.entity.Enterprise;
import com.hdw.enterprise.service.IEnterpriseService;
import com.hdw.system.service.ISysLogService;
import com.hdw.system.service.ISysUserService;
import com.hdw.system.shiro.ShiroKit;
import com.hdw.system.shiro.form.SysLoginForm;
import com.hdw.system.shiro.jwt.JwtUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 登录退出接口
 * @Author TuMinglong
 * @Date 2018/6/11 17:07
 */
@Slf4j
@Api(value = "登录退出接口", tags = {" 登录退出接口"})
@RestController
public class SysLoginController {

    @Reference
    private ISysUserService userService;

    @Reference
    private IEnterpriseService enterpriseService;

    @Autowired
    private IRedisService redisService;

    @Reference
    private ISysLogService sysLogService;

    //30分钟过期
    @Value("${hdw.expire}")
    private int expire;

    //登录用户Token令牌缓存KEY前缀
    @Value("${hdw.shiro.user-token-prefix}")
    private String userTokenPrefix;

    /**
     * 登录
     */
    @ApiOperation(value = "登录", notes = "登录")
    @PostMapping("/sys/login")
    public CommonResult login(@RequestBody SysLoginForm form) {
        if (StringUtils.isBlank(form.getUsername())) {
            return CommonResult.fail().msg("用户名不能为空");
        }
        if (StringUtils.isBlank(form.getPassword())) {
            return CommonResult.fail().msg("密码不能为空");
        }

        String username=decrypt(form.getUsername());
        String password=decrypt(form.getPassword());

        LoginUser loginUser = userService.selectByLoginName(username);

        if (null == loginUser) {
            return CommonResult.fail().msg("账号不存在");
        }
        if (!loginUser.getPassword().equals(ShiroKit.md5(password, loginUser.getLoginName() + loginUser.getSalt()))) {
            return CommonResult.fail().msg("密码不正确");
        }
        //当企业不存在或者企业被禁用不允许登录
        if (loginUser.getUserType() == 1) {
            Enterprise sysEnterprise = enterpriseService.getById(loginUser.getEnterpriseId());
            if (null != sysEnterprise && sysEnterprise.getStatus() == 1) {
                return CommonResult.fail().msg("企业被禁用，该账户不允许登录");
            } else if (null == sysEnterprise) {
                return CommonResult.fail().msg("企业不存在，该账户不允许登录");
            }
        }

        // 生成token
        String token = JwtUtil.generatorToken(loginUser.getLoginName(), loginUser.getPassword(), expire);
        // 设置token缓存有效时间
        redisService.set(userTokenPrefix + loginUser.getLoginName(), token, expire * 2);
        Map<String, Object> params = new HashMap<>();
        params.put("token", token);
        params.put("expire", expire);
        log.info(" 用户名:  " + loginUser.getName() + ",登录成功！ ");
        sysLogService.addLog(loginUser.getLoginName(),"用户名: " + loginUser.getName() + ",登录成功！", 1, null);
        return CommonResult.ok().data(params);
    }

    /**
     * 退出
     */
    @ApiOperation(value = "退出", notes = "退出")
    @PostMapping("/sys/logout")
    public CommonResult logout(HttpServletRequest request, HttpServletResponse response) {
        //用户退出逻辑
        String token = request.getHeader(CommonConstants.JWT_DEFAULT_TOKEN_NAME);
        if (StringUtils.isEmpty(token)) {
            return CommonResult.fail().msg("退出登录失败!");
        }
        String username = JwtUtil.getUsername(token);
        LoginUser loginUser = userService.selectByLoginName(username);
        if (loginUser != null) {
            log.info(" 用户名:  " + loginUser.getName() + ",退出成功！ ");
            //清空用户登录Token缓存
            redisService.del(userTokenPrefix + username);
            //清空用户登录Shiro权限缓存
            sysLogService.addLog(loginUser.getLoginName(),"用户名: " + loginUser.getName() + ",退出成功！", 1, null);
            return CommonResult.ok().msg("退出登录成功!");
        } else {
            return CommonResult.fail().msg("Token无效!");
        }
    }

    /**
     * 解密
     * @param encrypt
     * @return
     */
    private String decrypt(String encrypt) {
        /** AES加解密 */
        AES aes = new AES(Mode.CBC, Padding.PKCS5Padding, "1234567812345678".getBytes(), "1234567812345678".getBytes());
        // 解密
        String s= aes.decryptStr(encrypt).replace("\"", "");
        return s;
    }

}
