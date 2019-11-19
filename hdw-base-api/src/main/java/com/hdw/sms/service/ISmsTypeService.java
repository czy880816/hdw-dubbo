package com.hdw.sms.service;


import com.hdw.common.base.service.IBaseService;
import com.hdw.common.result.PageParam;
import com.hdw.sms.entity.SmsType;
import com.hdw.sms.param.SmsTypeParam;


/**
 * 消息类型与用户关系表
 *
 * @Author TuMinglong
 * @Date 2019-07-31 16:31:12
 */
public interface ISmsTypeService extends IBaseService<SmsType> {

    /**
     * 自定义分页
     * @param smsTypeParam
     * @return
     */
    PageParam selectSmsTypePageList(SmsTypeParam smsTypeParam);

}

