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
import java.util.List;

/**
 * @Description 消息类型与用户关系表
 * @Author TuMinglong
 * @Date 2019-07-31 16:31:12
 */
@ApiModel(value = "消息类型与用户关系表")
@TableName("t_sys_sms_type")
public class SmsType extends Model<SmsType> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    /**
     * 类型名称
     */
    @ApiModelProperty(value = "类型名称")
    @TableField("type_name")
    private String typeName;
    /**
     * 账号列表
     */
    @ApiModelProperty(value = "账号列表")
    @TableField("target_list")
    private String targetList;
    /**
     * 是否发送短信（0-是，0-否）
     */
    @ApiModelProperty(value = "是否发送短信（0-是，0-否）")
    @TableField("is_send_sms")
    private Integer isSendSms;
    /**
     * 是否发送邮件（0-是，1-否）
     */
    @ApiModelProperty(value = "是否发送邮件（0-是，1-否）")
    @TableField("is_send_email")
    private Integer isSendEmail;
    /**
     * 创建时间
     */
    @ApiModelProperty(value = "创建时间")
    @TableField("create_time")
    private Date createTime;
    /**
     * 更新时间
     */
    @ApiModelProperty(value = "更新时间")
    @TableField("update_time")
    private Date updateTime;
    /**
     * 创建者
     */
    @ApiModelProperty(value = "创建者")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("create_user")
    private Long createUser;
    /**
     * 修改者
     */
    @ApiModelProperty(value = "修改者")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableField("update_user")
    private Long updateUser;

    /**
     * 账号列表
     */
    @ApiModelProperty(value = "账号列表")
    @TableField(exist = false)
    private List<String> accountList;

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
     * 设置：类型名称
     */
    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    /**
     * 获取：类型名称
     */
    public String getTypeName() {
        return typeName;
    }

    /**
     * 设置：账号列表
     */
    public void setTargetList(String targetList) {
        this.targetList = targetList;
    }

    /**
     * 获取：账号列表
     */
    public String getTargetList() {
        return targetList;
    }

    /**
     * 设置：是否发送短信（0-是，0-否）
     */
    public void setIsSendSms(Integer isSendSms) {
        this.isSendSms = isSendSms;
    }

    /**
     * 获取：是否发送短信（0-是，0-否）
     */
    public Integer getIsSendSms() {
        return isSendSms;
    }

    /**
     * 设置：是否发送邮件（0-是，1-否）
     */
    public void setIsSendEmail(Integer isSendEmail) {
        this.isSendEmail = isSendEmail;
    }

    /**
     * 获取：是否发送邮件（0-是，1-否）
     */
    public Integer getIsSendEmail() {
        return isSendEmail;
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
     * 设置：更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取：更新时间
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

    public List<String> getAccountList() {
        return accountList;
    }

    public void setAccountList(List<String> accountList) {
        this.accountList = accountList;
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
