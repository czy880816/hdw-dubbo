package com.hdw.enterprise.param;

import com.hdw.common.param.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description com.hdw.enterprise.param
 * @Author TuMingLong
 * @Date 2019/11/7 10:36
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("查询消息类型参数对象")
public class EnterpriseJobParam extends QueryParam {

    @ApiModelProperty("主键ID")
    private String id;
    @ApiModelProperty("企业ID")
    private String enterpriseId;
    @ApiModelProperty("职位代码")
    private String jobCode;
    @ApiModelProperty("职位名称")
    private String jobName;
    @ApiModelProperty("部门ID")
    private String departmentId;
    @ApiModelProperty(value = "用户ID", hidden = true)
    private Long userId;
}
