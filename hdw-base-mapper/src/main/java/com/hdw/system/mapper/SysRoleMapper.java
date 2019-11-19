package com.hdw.system.mapper;

import com.hdw.common.base.mapper.SuperMapper;
import com.hdw.system.entity.SysRole;
import com.hdw.system.entity.vo.RoleVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 角色表
 *
 * @author TuMinglong
 * @date 2018-12-11 11:35:15
 */
public interface SysRoleMapper extends SuperMapper<SysRole> {

    /**
     * 多表信息查询
     *
     * @param param
     * @return
     */
    List<SysRole> selectSysRoleList(@Param("param") Map<String, Object> param);

    RoleVo selectByUserId(@Param("userId") Long userId);

    RoleVo selectByRoleId(@Param("roleId") Long roleId);


}
