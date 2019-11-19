package com.hdw.system.service.impl;


import com.hdw.common.base.entity.LoginUser;
import com.hdw.common.base.service.impl.BaseServiceImpl;
import com.hdw.common.constants.CommonConstants;
import com.hdw.common.exception.GlobalException;
import com.hdw.system.entity.SysResource;
import com.hdw.system.entity.SysUser;
import com.hdw.system.mapper.SysUserMapper;
import com.hdw.system.service.ISysResourceService;
import com.hdw.system.service.ISysUserEnterpriseService;
import com.hdw.system.service.ISysUserRoleService;
import com.hdw.system.service.ISysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 用户表
 *
 * @author TuMinglong
 * @date 2018-12-11 11:35:15
 */
@Slf4j
@Service(interfaceName = "ISysUserService")
@Transactional(rollbackFor = Exception.class)
public class SysUserServiceImpl extends BaseServiceImpl<SysUserMapper, SysUser> implements ISysUserService {

    // 控制线程数，最优选择是处理器线程数*3，本机处理器是4线程
    private final static int THREAD_COUNT = Runtime.getRuntime().availableProcessors()*3;

    @Autowired
    private ISysUserRoleService sysUserRoleService;
    @Autowired
    private ISysUserEnterpriseService sysUserEnterpriseService;
    @Autowired
    private ISysResourceService sysResourceService;

    @Override
    public LoginUser selectByUserId(Long userId) {
        if (userId == null) {
            return null;
        }
        SysUser sysUser = this.baseMapper.selectById(userId);
        if (sysUser == null) {
            return null;
        }
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(sysUser, loginUser);
        List<String> enterpriseIds = sysUserEnterpriseService.selectEnterpriseIdByUserId(sysUser.getId());
        loginUser.setEnterpriseIdList(enterpriseIds);
        return loginUser;
    }

    @Override
    public LoginUser selectByLoginName(String loginName) {

        if (StringUtils.isBlank(loginName)) {
            return null;
        }
        SysUser sysUser = this.baseMapper.selectByLoginName(loginName);
        if (sysUser == null) {
            return null;
        }
        LoginUser loginUser = new LoginUser();
        BeanUtils.copyProperties(sysUser, loginUser);
        List<String> enterpriseIds = sysUserEnterpriseService.selectEnterpriseIdByUserId(sysUser.getId());
        loginUser.setEnterpriseIdList(enterpriseIds);
        return loginUser;
    }

    @Override
    public void saveByVo(SysUser user) {
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
        this.baseMapper.insert(user);
        //检查角色是否越权
        checkRole(user);

        pool.submit(new Runnable() {
            @Override
            public void run() {
                //保存用户与角色关系
                sysUserRoleService.saveOrUpdateUserRole(user.getId(), user.getRoleIdList());
            }
        });
        pool.submit(new Runnable() {
            @Override
            public void run() {
                //保存用户与企业关系关系
                sysUserEnterpriseService.saveOrUpdateUserEnterprise(user.getId(), user.getEnterpriseIdList());
            }
        });
        pool.shutdown();
    }

    @Override
    public void updateByVo(SysUser user) {
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
        this.updateById(user);
        //检查角色是否越权
        checkRole(user);
        pool.submit(new Runnable() {
            @Override
            public void run() {
                //保存用户与角色关系
                sysUserRoleService.saveOrUpdateUserRole(user.getId(), user.getRoleIdList());
            }
        });
        pool.submit(new Runnable() {
            @Override
            public void run() {
                //保存用户与企业关系关系
                sysUserEnterpriseService.saveOrUpdateUserEnterprise(user.getId(), user.getEnterpriseIdList());
            }
        });
        pool.shutdown();
    }

    @Override
    public List<Long> selectResourceIdListByUserId(Long userId) {
        return this.baseMapper.selectResourceIdListByUserId(userId);
    }

    @Override
    public void deleteBatch(Long[] userIds) {
        this.removeByIds(Arrays.asList(userIds));
        //删除用户与角色关系
        sysUserRoleService.deleteBatchByUserIds(userIds);
        //删除监管用户与企业关系
        sysUserEnterpriseService.deleteBatchByUserIds(userIds);
    }

    @Override
    public Set<String> selectUserPermissions(long userId) {
        List<String> permsList;
        //系统管理员，拥有最高权限
        if (userId == CommonConstants.SUPER_ADMIN) {
            List<SysResource> menuList = sysResourceService.list();
            permsList = new ArrayList<>(menuList.size());
            for (SysResource menu : menuList) {
                permsList.add(menu.getUrl());
            }
        } else {
            permsList = this.baseMapper.selectPerms(userId);
        }
        //用户权限列表
        Set<String> permsSet = new HashSet<>();
        for (String perms : permsList) {
            if (StringUtils.isBlank(perms)) {
                continue;
            }
            permsSet.addAll(Arrays.asList(perms.trim().split(",")));
        }
        return permsSet;
    }

    @Override
    public Set<String> selectUserRoles(long userId) {
        List<String> roleList = this.baseMapper.selectRoles(userId);
        return new HashSet<>(roleList);
    }

    /**
     * 检查角色是否越权
     */
    private void checkRole(SysUser user) {
        if (user.getRoleIdList() == null || user.getRoleIdList().size() == 0) {
            return;
        }
        //如果不是超级管理员，则需要判断用户的角色是否自己创建
        if (user.getCreateUserId() == CommonConstants.SUPER_ADMIN) {
            return;
        }
        //查询用户创建的角色列表
        List<Long> roleIdList = sysUserRoleService.selectRoleIdListByUserId(user.getCreateUserId());

        //判断是否越权
        if (!roleIdList.containsAll(user.getRoleIdList())) {
            throw new GlobalException("新增用户所选角色，不是本人创建");
        }
    }
}
