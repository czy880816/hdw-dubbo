package com.hdw.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hdw.common.utils.JacksonUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Description vue select选择器对象
 * @Author TuMinglong
 * @Date 2018/6/20 10:38
 */
@ApiModel("vue select选择器对象")
public class SelectNode implements Serializable {
    /**
     * value
     */
    @ApiModelProperty("key")
    private String value;

    /**
     * label
     */
    @ApiModelProperty("value")
    private String label;

    @ApiModelProperty("对象List")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SelectNode> options;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<SelectNode> getOptions() {
        return options;
    }

    public void setOptions(List<SelectNode> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return JacksonUtils.toJson(this);
    }
}
