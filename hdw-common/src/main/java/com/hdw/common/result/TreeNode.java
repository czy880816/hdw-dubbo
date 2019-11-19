package com.hdw.common.result;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hdw.common.utils.JacksonUtils;

import java.io.Serializable;
import java.util.List;

/**
 * @Description vue 树形控件对象
 * @Author TuMinglong
 * @Date 2019/8/8 11:39
 */
public class TreeNode implements Serializable {

    /**
     * id
     */
    private String id;

    /**
     * label
     */
    private String label;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<TreeNode> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return JacksonUtils.toJson(this);
    }
}
