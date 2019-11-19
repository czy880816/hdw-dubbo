package com.hdw.common.result;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.hdw.common.utils.JacksonUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.List;

/**
 * @Description vue 树形选择器对象
 * @Author TuMinglong
 * @Date 2018/12/13 18:37
 */
@ApiModel("vue 树形选择器对象")
public class SelectTreeNode implements Serializable {

    // 节点ID
    @ApiModelProperty("节点ID")
    private String id;
    // 父节点ID
    @ApiModelProperty("父节点ID")
    private String parentId;
    // 节点名称
    @ApiModelProperty("节点名称")
    private String name;
    // 排序
    @ApiModelProperty("排序")
    private String rank;
    @ApiModelProperty("children")
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private List<SelectTreeNode> children;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    public List<SelectTreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<SelectTreeNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return JacksonUtils.toJson(this);
    }

    public void add(SelectTreeNode node) {
        children.add(node);
    }

    public static SelectTreeNode createParent() {
        SelectTreeNode selectTreeNode = new SelectTreeNode();
        selectTreeNode.setId("0");
        selectTreeNode.setName("顶级");
        selectTreeNode.setParentId("0");
        selectTreeNode.setRank("1");
        return selectTreeNode;
    }
}
