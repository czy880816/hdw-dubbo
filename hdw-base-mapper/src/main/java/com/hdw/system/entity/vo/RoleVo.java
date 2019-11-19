package com.hdw.system.entity.vo;


import com.hdw.system.entity.SysResource;
import com.hdw.system.entity.SysRole;

import java.io.Serializable;
import java.util.List;


/**
 * @description：UserVo
 * @author：TuMinglong
 * @date 2018年5月6日 上午9:55:46
 */
public class RoleVo extends SysRole implements Serializable {

    // 拥有的权限列表
    private List<SysResource> permissions;

    public List<SysResource> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<SysResource> permissions) {
        this.permissions = permissions;
    }


}
