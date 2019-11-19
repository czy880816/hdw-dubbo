package com.hdw.common.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description com.hdw.common.param
 * @Author TuMingLong
 * @Date 2019/11/6 9:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("ID参数")
public class IdStatusParam implements Serializable {

    @ApiModelProperty("ID")
    @NotBlank(message = "ID不能为空")
    private String id;

    @ApiModelProperty("状态,0:启用 1:禁用")
    @NotNull(message = "状态不能为空")
    private Integer status;
}
