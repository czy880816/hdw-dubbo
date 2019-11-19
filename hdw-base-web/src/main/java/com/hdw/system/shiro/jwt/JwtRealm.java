package com.hdw.system.shiro.jwt;

import com.hdw.common.base.entity.LoginUser;
import com.hdw.common.config.redis.IRedisService;
import com.hdw.common.constants.CommonConstants;
import com.hdw.common.exception.SysLoginException;
import com.hdw.common.utils.StringUtils;
import com.hdw.system.service.*;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Set;

/**
 * @Description Shiro 授权认证
 * @Author TuMingLong
 * @Date 2019/10/31 11:50
 */
@Slf4j
public class JwtRealm extends AuthorizingRealm {

    @Reference
    private ISysUserService sysUserService;
    @Autowired
    private IRedisService redisService;

    //过期时间
    @Value("${hdw.expire}")
    private int expire;

    //登录用户Token令牌缓存KEY前缀
    @Value("${hdw.shiro.user-token-prefix}")
    private String userTokenPrefix;


    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }


    /**
     * 授权认证，设置角色/权限信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        // TODO 被调用两次
        log.info("doGetAuthorizationInfo principalCollection...");
        Long userId = null;
        if (principalCollection != null) {
            LoginUser loginUser = (LoginUser) principalCollection.getPrimaryPrincipal();
            userId = loginUser.getId();
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        // 设置用户拥有的角色集合
        Set<String> roleSet = sysUserService.selectUserRoles(userId);
        info.setRoles(roleSet);

        // 设置用户拥有的权限集合
        Set<String> permissionSet = sysUserService.selectUserPermissions(userId);
        info.addStringPermissions(permissionSet);
        return info;
    }

    /**
     * 登录认证
     *
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        log.info("doGetAuthenticationInfo authenticationToken...");
        // 校验token
        String token = (String) authenticationToken.getCredentials();
        if (token == null) {
            throw new SysLoginException("token为空！");
        }
        // 校验token有效性
        LoginUser loginUser = this.checkUserTokenIsEffect(token);
        return new SimpleAuthenticationInfo(loginUser, token, getName());
    }


    /**
     * 校验token的有效性
     *
     * @param token
     */
    public LoginUser checkUserTokenIsEffect(String token) throws AuthenticationException {
        // 解密获得username，用于和数据库进行对比
        String username = JwtUtil.getUsername(token);
        if (username == null) {
            throw new SysLoginException("token非法无效!");
        }

        // 查询用户信息
        log.info("———校验token是否有效————checkUserTokenIsEffect——————— " + token);
        LoginUser loginUser = sysUserService.selectByLoginName(username);
        if (loginUser == null) {
            throw new SysLoginException("用户不存在!");
        }
        // 判断用户状态
        if (loginUser.getStatus() == 1) {
            throw new SysLoginException("账号已被锁定,请联系管理员!");
        }
        // 校验token是否超时失效 & 或者账号密码是否错误
        if (!jwtTokenRefresh(token, username, loginUser.getPassword())) {
            throw new SysLoginException("Token失效，请重新登录!");
        }
        return loginUser;
    }

    /**
     * JWTToken刷新生命周期 （实现： 用户在线操作不掉线功能）
     * 1、登录成功后将用户的JWT生成的Token作为k、v存储到cache缓存里面，缓存有效期设置为Jwt有效时间的2倍
     * 2、当该用户再次请求时，通过JWTFilter层层校验之后会进入到doGetAuthenticationInfo进行身份验证
     * 3、当该用户这次请求jwt生成的token值已经超时，但该token对应cache中的k还是存在，则表示该用户一直在操作只是JWT的token失效了，程序会给token对应的k映射的v值重新生成JWTToken并覆盖v值，该缓存生命周期重新计算
     * 4、当该用户这次请求jwt在生成的token值已经超时，并在cache中不存在对应的k，则表示该用户账户空闲超时，返回用户信息已失效，请重新登录。
     * 注意： 前端请求Header中设置Authorization保持不变，校验有效性以缓存中的token为准。
     * 用户过期时间 = Jwt有效时间 * 2。
     *
     * @param token
     * @param username
     * @param password
     * @return
     */
    public boolean jwtTokenRefresh(String token, String username, String password) {
        String tokenKey = userTokenPrefix+ username;
        String cacheToken = (String) redisService.get(tokenKey);
        if (StringUtils.isNotBlank(cacheToken)) {
            // 校验token有效性
            if (!JwtUtil.verifyToken(token, username, password)) {
                String newAuthorization = JwtUtil.generatorToken(username, password, expire);
                // 设置超时时间
                redisService.set(tokenKey, newAuthorization, expire * 2);
                log.info("——————————用户在线操作，更新token保证不掉线—————————jwtTokenRefresh——————— " + token);
            }
            return true;
        }
        return false;
    }

    /**
     * 清除当前用户的权限认证缓存
     *
     * @param principals 权限信息
     */
    @Override
    public void clearCache(PrincipalCollection principals) {
        super.clearCache(principals);
    }
}
