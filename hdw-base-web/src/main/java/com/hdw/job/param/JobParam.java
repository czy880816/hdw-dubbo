package com.hdw.job.param;

import com.hdw.common.param.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description com.hdw.job.param
 * @Author TuMingLong
 * @Date 2019/11/6 21:26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("查询定时任务日志参数对象")
public class JobParam extends QueryParam {

    @ApiModelProperty(value = "定时任务ID")
    private String jobId;

    @ApiModelProperty(value = "bean名称")
    private String beanName;
}
