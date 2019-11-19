package com.hdw.sms.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.hdw.common.utils.JacksonUtils;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.util.Date;


/**
 * @Description 消息表
 * @Author TuMinglong
 * @Date 2019-07-31 16:31:12
 */
@ApiModel(value = "消息表")
@TableName("t_sys_sms")
public class SysSms extends Model<SysSms> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    /**
     * 消息类型ID
     */
    @ApiModelProperty(value = "消息类型ID")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("sms_type_id")
    private Long smsTypeId;
    /**
     * 消息标题
     */
    @ApiModelProperty(value = "消息标题")
    private String title;
    /**
     * 消息内容
     */
    @ApiModelProperty(value = "消息内容")
    private String content;
    /**
     * 推送时间
     */
    @ApiModelProperty(value = "推送时间")
    @TableField("sms_time")
    private Date smsTime;
    /**
     * 推送次数
     */
    @ApiModelProperty(value = "推送次数")
    @TableField("sms_count")
    private Integer smsCount;
    /**
     * 推送间隔时间（秒）
     */
    @ApiModelProperty(value = "推送间隔时间（秒）")
    @TableField("interval_time")
    private Integer intervalTime;
    /**
     * 状态（0-正常，1-禁止）
     */
    @ApiModelProperty(value = "状态（0-正常，1-禁止）	")
    private Integer status;
    /**
     * 每次推送时间
     */
    @ApiModelProperty(value = "每次推送时间")
    @TableField("real_time")
    private Date realTime;
    /**
     * 推送真实次数
     */
    @ApiModelProperty(value = "推送真实次数")
    @TableField("real_count")
    private Integer realCount;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;
    /**
     * 修改时间
     */
    @ApiModelProperty(value = "修改时间")
    @TableField("update_time")
    private Date updateTime;
    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    @TableField("create_user")
    private Long createUser;
    /**
     * 修改者
     */
    @ApiModelProperty(value = "修改者")
    @TableField("update_user")
    private Long updateUser;

    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名称")
    @TableField(exist = false)
    private String typeName;

    /**
     * 设置：主键
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取：主键
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置：消息类型ID
     */
    public void setSmsTypeId(Long smsTypeId) {
        this.smsTypeId = smsTypeId;
    }

    /**
     * 获取：消息类型ID
     */
    public Long getSmsTypeId() {
        return smsTypeId;
    }

    /**
     * 设置：消息标题
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取：消息标题
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置：消息内容
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * 获取：消息内容
     */
    public String getContent() {
        return content;
    }

    /**
     * 设置：推送时间
     */
    public void setSmsTime(Date smsTime) {
        this.smsTime = smsTime;
    }

    /**
     * 获取：推送时间
     */
    public Date getSmsTime() {
        return smsTime;
    }

    /**
     * 设置：推送次数
     */
    public void setSmsCount(Integer smsCount) {
        this.smsCount = smsCount;
    }

    /**
     * 获取：推送次数
     */
    public Integer getSmsCount() {
        return smsCount;
    }

    /**
     * 设置：推送间隔时间（秒）
     */
    public void setIntervalTime(Integer intervalTime) {
        this.intervalTime = intervalTime;
    }

    /**
     * 获取：推送间隔时间（秒）
     */
    public Integer getIntervalTime() {
        return intervalTime;
    }

    /**
     * 设置：状态（0-正常，1-禁止）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态（0-正常，1-禁止）
     */
    public Integer getStatus() {
        return status;
    }

    /**
     * 设置：每次推送时间
     */
    public void setRealTime(Date realTime) {
        this.realTime = realTime;
    }

    /**
     * 获取：每次推送时间
     */
    public Date getRealTime() {
        return realTime;
    }

    /**
     * 设置：推送真实次数
     */
    public void setRealCount(Integer realCount) {
        this.realCount = realCount;
    }

    /**
     * 获取：推送真实次数
     */
    public Integer getRealCount() {
        return realCount;
    }

    /**
     * 设置：创建时间
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取：创建时间
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置：修改时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：修改时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置：创建者
     */
    public void setCreateUser(Long createUser) {
        this.createUser = createUser;
    }

    /**
     * 获取：创建者
     */
    public Long getCreateUser() {
        return createUser;
    }

    /**
     * 设置：修改者
     */
    public void setUpdateUser(Long updateUser) {
        this.updateUser = updateUser;
    }

    /**
     * 获取：修改者
     */
    public Long getUpdateUser() {
        return updateUser;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    protected Serializable pkVal() {
        return this.id;
    }

    @Override
    public String toString() {
        return JacksonUtils.toJson(this);
    }
}
