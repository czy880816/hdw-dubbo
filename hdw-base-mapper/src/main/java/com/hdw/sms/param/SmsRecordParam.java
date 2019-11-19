package com.hdw.sms.param;

import com.hdw.common.param.QueryParam;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description com.hdw.sms.param
 * @Author TuMingLong
 * @Date 2019/11/7 10:10
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("查询消息记录参数对象")
public class SmsRecordParam extends QueryParam {
    @ApiModelProperty("用户ID")
    private String userId;
    @ApiModelProperty("用户名")
    private String userName;
    @ApiModelProperty("开始时间")
    private String startTime;
    @ApiModelProperty("结束时间")
    private String endTime;
    @ApiModelProperty(value = "状态(-1:未读)", example = "-1")
    private int status;
}
