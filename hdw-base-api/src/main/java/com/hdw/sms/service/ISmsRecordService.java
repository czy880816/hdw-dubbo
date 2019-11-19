package com.hdw.sms.service;

import com.hdw.common.base.service.IBaseService;
import com.hdw.common.result.PageParam;
import com.hdw.sms.entity.SmsRecord;
import com.hdw.sms.param.SmsRecordParam;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 消息记录表
 *
 * @Author TuMinglong
 * @Date 2019-07-31 16:31:12
 */
public interface ISmsRecordService extends IBaseService<SmsRecord> {

    /**
     * 自定义分页
     * @param smsRecordParam
     * @return
     */
    PageParam selectSmsRecordPageList(SmsRecordParam smsRecordParam);

    /**
     * 未读消息数量
     *
     * @param userId
     * @return
     */
    Integer findUnreadMessagesCount(Long userId);

    /**
     * 最近5条未读消息
     *
     * @param userId
     * @return
     */
    List<SmsRecord> findRecent5Messages(@Param("userId") Long userId);

    /**
     * 更改状态已读
     *
     * @param smsRecordId
     */
    void updateMessageStatus(String[] smsRecordId);

}

