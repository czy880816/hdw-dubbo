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
 * @Description 消息记录表
 * @Author TuMinglong
 * @Date 2019-07-31 16:31:12
 */
@ApiModel(value = "消息记录表")
@TableName("t_sys_sms_record")
public class SmsRecord extends Model<SmsRecord> {
    private static final long serialVersionUID = 1L;

    /**
     * 主键
     */
    @ApiModelProperty(value = "主键")
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId(type = IdType.ID_WORKER)
    private Long id;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @TableField("user_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;
    /**
     * 消息ID
     */
    @ApiModelProperty(value = "消息ID")
    @TableField("sms_id")
    @JsonSerialize(using = ToStringSerializer.class)
    private Long smsId;
    /**
     * 要求推送时间
     */
    @ApiModelProperty(value = "要求推送时间")
    @TableField("sms_time")
    private Date smsTime;
    /**
     * 实际推送时间
     */
    @ApiModelProperty(value = "实际推送时间")
    @TableField("push_time")
    private Date pushTime;
    /**
     * 状态（0-待推送，1-推送成功，2-推送失败，3-已读）
     */
    @ApiModelProperty(value = "状态（0-待推送，1-推送成功，2-推送失败，3-已读）")
    private Integer status;

    /**
     * 消息距当前时间
     */
    @ApiModelProperty(value = "消息距当前时间")
    @TableField(exist = false)
    private String timeStr;

    @ApiModelProperty(value = "用户名")
    @TableField(exist = false)
    private String userName;

    @ApiModelProperty(value = "消息标题")
    @TableField(exist = false)
    private String title;

    @ApiModelProperty(value = "消息内容")
    @TableField(exist = false)
    private String content;

    @ApiModelProperty(value = "搜索开始时间",hidden = true)
    @TableField(exist = false)
    private String startTime;

    @ApiModelProperty(value = "搜索结束时间",hidden = true)
    @TableField(exist = false)
    private String endTime;


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
     * 设置：账号
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * 获取：账号
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * 设置：消息ID
     */
    public void setSmsId(Long smsId) {
        this.smsId = smsId;
    }

    /**
     * 获取：消息ID
     */
    public Long getSmsId() {
        return smsId;
    }

    /**
     * 设置：要求推送时间
     */
    public void setSmsTime(Date smsTime) {
        this.smsTime = smsTime;
    }

    /**
     * 获取：要求推送时间
     */
    public Date getSmsTime() {
        return smsTime;
    }

    /**
     * 设置：实际推送时间
     */
    public void setPushTime(Date pushTime) {
        this.pushTime = pushTime;
    }

    /**
     * 获取：实际推送时间
     */
    public Date getPushTime() {
        return pushTime;
    }

    /**
     * 设置：状态（0-待推送，1-推送成功，2-推送失败，3-已读）
     */
    public void setStatus(Integer status) {
        this.status = status;
    }

    /**
     * 获取：状态（0-待推送，1-推送成功，2-推送失败，3-已读）
     */
    public Integer getStatus() {
        return status;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getTimeStr() {
        return timeStr;
    }

    public void setTimeStr(String timeStr) {
        this.timeStr = timeStr;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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
