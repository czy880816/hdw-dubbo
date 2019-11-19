package com.hdw.common.param;

import com.hdw.common.constants.CommonConstants;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @Description 查询参数
 * @Author TuMingLong
 * @Date 2019/11/6 9:22
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("查询参数对象")
public class QueryParam implements Serializable {

    @ApiModelProperty(value = "页码,默认为1", example = "1")
    @NotNull(message = "页码不能为空")
    private long page = CommonConstants.DEFAULT_PAGE;

    @ApiModelProperty(value = "行数,默认为10", example = "10")
    @NotNull(message = "行数不能为空")
    private long limit = CommonConstants.DEFAULT_LIMIT;

    @ApiModelProperty(value = "搜索字符串", example = "")
    private String key;

    public void setPage(long page) {
        if (page <= 0) {
            this.page = CommonConstants.DEFAULT_PAGE;
        } else {
            this.page = page;
        }
    }

    public void setLimit(long limit) {
        if (limit <= 0) {
            this.limit = CommonConstants.DEFAULT_LIMIT;
        } else if (this.limit >= CommonConstants.MAX_LIMIT) {
            this.limit = CommonConstants.MAX_LIMIT;
        } else {
            this.limit = limit;
        }
    }
}
