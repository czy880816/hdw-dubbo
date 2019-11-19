package com.hdw.system.controller;

import com.hdw.common.base.entity.LoginUser;
import com.hdw.common.constants.CommonConstants;
import com.hdw.common.exception.BusinessException;
import com.hdw.common.result.CommonResult;
import com.hdw.common.result.PageParam;
import com.hdw.common.result.SelectNode;
import com.hdw.common.validator.group.CreateGroup;
import com.hdw.common.validator.group.UpdateGroup;
import com.hdw.system.entity.SysUser;
import com.hdw.system.entity.vo.UserVo;
import com.hdw.system.param.UserParam;
import com.hdw.system.service.ISysUserEnterpriseService;
import com.hdw.system.service.ISysUserRoleService;
import com.hdw.system.service.ISysUserService;
import com.hdw.system.shiro.ShiroKit;
import com.hdw.system.shiro.form.PasswordForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description 用户表接口
 * @Author TuMinglong
 * @Date 2018/12/13 11:42
 */
@Slf4j
@Api(value = "用户表接口", tags = {"用户表接口"})
@RestController
@RequestMapping("sys/user")
public class SysUserController {

    @Reference
    private ISysUserService sysUserService;
    @Reference
    private ISysUserRoleService sysUserRoleService;
    @Reference
    private ISysUserEnterpriseService sysUserEnterpriseService;


    /**
     * 所有用户列表
     */
    @ApiOperation(value = "用户列表", notes = "用户列表")
    @GetMapping("/list")
    @RequiresPermissions("sys/user/list")
    public CommonResult<PageParam<UserVo>> list(UserParam userParam) {
        //只有超级管理员，才能查看所有管理员列表
        Long currUserId = ShiroKit.getUser().getId();
        if (currUserId != CommonConstants.SUPER_ADMIN) {
            userParam.setCreateUserId(currUserId);
        }
        PageParam<UserVo> page = sysUserService.pageList(userParam);

        return CommonResult.ok().data(page);
    }

    /**
     * 修改登录用户密码
     */
    @ApiOperation(value = "修改登录用户密码", notes = "修改登录用户密码")
    @PostMapping("/password")
    public CommonResult password(@RequestBody PasswordForm form) {
        if (StringUtils.isBlank(form.getNewPassword())) {
            return CommonResult.fail().msg("新密码不为能空");
        }
        SysUser user = sysUserService.getById(ShiroKit.getUser().getId());
        String password = ShiroKit.md5(form.getPassword(), user.getLoginName() + user.getSalt());
        if (!user.getPassword().equals(password)) {
            return CommonResult.fail().msg("原密码不正确");
        }
        String newPassword = ShiroKit.md5(form.getNewPassword(), user.getLoginName() + user.getSalt());
        user.setPassword(newPassword);
        user.setUpdateTime(new Date());
        sysUserService.updateById(user);
        return CommonResult.ok().msg("密码修改成功");
    }

    /**
     * 登录用户信息
     */
    @ApiOperation(value = "登录用户信息", notes = "登录用户信息")
    @GetMapping("/info")
    public CommonResult<LoginUser> info() {

        return CommonResult.ok().data(ShiroKit.getUser());
    }

    /**
     * 用户信息
     */
    @ApiOperation(value = "用户信息", notes = "用户信息")
    @ApiImplicitParam(paramType = "path", name = "userId", value = "主键ID", dataType = "Integer", required = true)
    @GetMapping("/info/{userId}")
    @RequiresPermissions("sys/user/info")
    public CommonResult<SysUser> info(@PathVariable("userId") Long userId) {
        SysUser user = sysUserService.getById(userId);
        List<Long> roleIdList = sysUserRoleService.selectRoleIdListByUserId(userId);
        user.setRoleIdList(roleIdList);
        List<String> enterpriseIdList = sysUserEnterpriseService.selectEnterpriseIdByUserId(userId);
        user.setEnterpriseIdList(enterpriseIdList);
        return CommonResult.ok().data(user);

    }

    /**
     * 保存用户
     */
    @ApiOperation(value = "保存用户", notes = "保存用户")
    @PostMapping("/save")
    @RequiresPermissions("sys/user/save")
    public CommonResult save(@RequestBody @Validated(CreateGroup.class) SysUser user) {
        try {
            LoginUser u = sysUserService.selectByLoginName(user.getLoginName());
            if (u != null) {
                return CommonResult.fail().msg("登录名已存在");
            }
            String salt = ShiroKit.getRandomSalt(16);
            user.setSalt(salt);
            String pwd = ShiroKit.md5(user.getPassword(), user.getLoginName() + salt);
            user.setPassword(pwd);
            user.setCreateTime(new Date());
            user.setCreateUserId(ShiroKit.getUser().getId());
            sysUserService.saveByVo(user);
            return CommonResult.ok().msg("添加成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException("运行异常，请联系管理员");
        }
    }

    /**
     * 修改用户
     */
    @PostMapping("/update")
    @RequiresPermissions("sys/user/update")
    public CommonResult update(@RequestBody @Validated(UpdateGroup.class) SysUser user) {
        try {
            if (StringUtils.isNotBlank(user.getPassword())) {
                String salt = ShiroKit.getRandomSalt(16);
                user.setSalt(salt);
                String pwd = ShiroKit.md5(user.getPassword(), user.getLoginName() + salt);
                user.setPassword(pwd);
            } else {
                user.setPassword(null);
            }
            user.setUpdateTime(new Date());
            user.setCreateUserId(ShiroKit.getUser().getId());
            sysUserService.updateByVo(user);
            return CommonResult.ok().msg("修改成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException("运行异常，请联系管理员");
        }
    }

    /**
     * 删除用户
     */
    @ApiOperation(value = "删除用户", notes = "删除用户")
    @ApiImplicitParam(name = "userIds", allowMultiple = true, dataType = "Integer", required = true, value = "用户ID数组", paramType = "query")
    @PostMapping("/delete")
    @RequiresPermissions("sys/user/delete")
    public CommonResult delete(@RequestBody Long[] userIds) {
        if (ArrayUtils.contains(userIds, CommonConstants.SUPER_ADMIN)) {
            return CommonResult.fail().msg("系统管理员不能删除");
        }
        if (ArrayUtils.contains(userIds, ShiroKit.getUser().getId())) {
            return CommonResult.fail().msg("当前用户不能删除");
        }
        sysUserService.deleteBatch(userIds);
        return CommonResult.ok();
    }

    /**
     * 用户选择树
     *
     * @return
     */
    @ApiOperation(value = "用户选择树", notes = "用户选择树")
    @GetMapping("/getUserTree")
    public CommonResult<List<SelectNode>> getUserTree() {
        try {
            List<SelectNode> nodeList = new ArrayList<>();
            List<SysUser> list = sysUserService.list();
            list.forEach(baseUser -> {
                SelectNode node = new SelectNode();
                node.setLabel(baseUser.getName());
                node.setValue(baseUser.getId().toString());
                nodeList.add(node);
            });
            return CommonResult.ok().data(nodeList);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new BusinessException("运行异常，请联系管理员");
        }
    }
}
