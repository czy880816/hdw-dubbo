package com.hdw.common.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * @Description com.hdw.common.param
 * @Author TuMingLong
 * @Date 2019/11/6 9:32
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("ID参数")
public class IdParam implements Serializable {

    @ApiModelProperty("ID")
    @NotBlank(message = "ID不能为空")
    private String id;
}
