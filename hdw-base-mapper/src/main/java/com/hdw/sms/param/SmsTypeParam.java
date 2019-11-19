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
@ApiModel("查询消息类型参数对象")
public class SmsTypeParam extends QueryParam {

    @ApiModelProperty("类型名称")
    private String typeName;
}
