package com.hdw.system.entity.vo;

import com.hdw.system.entity.SysResource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

/**
 * @Description 菜单VO
 * @Author TuMingLong
 * @Date 2019/11/6 14:38
 */
@ApiModel("菜单VO")
@Data
public class MenuVo implements Serializable {

    @ApiModelProperty(value = "菜单列表")
    List<SysResource> menuList;

    @ApiModelProperty(value = "权限列表")
    Set<String> permissions;
}
