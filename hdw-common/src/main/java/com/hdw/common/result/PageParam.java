package com.hdw.common.result;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Maps;
import com.hdw.common.constants.CommonConstants;
import com.hdw.common.param.QueryParam;
import com.hdw.common.utils.BeanConvertUtils;
import com.hdw.common.utils.JacksonUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @Description 分页参数
 * @Author TuMingLong
 * @Date 2019/11/6 9:44
 */
@ApiModel("分页参数")
@Data
@EqualsAndHashCode(callSuper = false)
public class PageParam<T> implements Serializable {

    //总行数
    @ApiModelProperty("总行数")
    @JSONField(name = "totalCount")
    @JsonProperty("totalCount")
    private int totalCount;

    //列表数据
    @ApiModelProperty("数据列表")
    @JSONField(name = "list")
    @JsonProperty("list")
    private List<T> list = Collections.emptyList();

    public PageParam() {

    }

    /**
     * 分页
     */
    public PageParam(IPage page) {
        this.list = page.getRecords();
        this.totalCount = new Long(page.getTotal()).intValue();
    }
}
