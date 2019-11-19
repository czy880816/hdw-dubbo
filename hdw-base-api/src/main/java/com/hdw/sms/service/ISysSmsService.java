package com.hdw.sms.service;

import com.hdw.common.base.service.IBaseService;
import com.hdw.common.result.PageParam;
import com.hdw.sms.entity.SysSms;
import com.hdw.sms.param.SmsParam;

import java.util.List;


/**
 * 消息表
 *
 * @Author TuMinglong
 * @Date 2019-07-31 16:31:12
 */
public interface ISysSmsService extends IBaseService<SysSms> {

    /**
     * 自定义分页
     * @param smsParam
     * @return
     */
    PageParam selectSmsPageList(SmsParam smsParam);

    /**
     * 获取待推送消息
     *
     * @return
     */
    List<SysSms> selectCurrentSmsList();

}

