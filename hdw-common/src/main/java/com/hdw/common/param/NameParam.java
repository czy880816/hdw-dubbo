package com.hdw.common.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * @Description com.hdw.common.param
 * @Author TuMingLong
 * @Date 2019/11/6 9:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("名称参数")
public class NameParam implements Serializable {

    @ApiModelProperty("名称")
    private String name;
}
