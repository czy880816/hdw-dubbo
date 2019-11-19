package com.hdw.sms.util;


import com.hdw.common.utils.JacksonUtils;
import com.hdw.sms.entity.Sms;
import com.hdw.sms.entity.SmsRecord;
import com.hdw.sms.entity.SmsType;
import com.hdw.sms.entity.SysSms;
import com.hdw.sms.service.ISmsRecordService;
import com.hdw.sms.service.ISmsTypeService;
import com.hdw.sms.service.ISysSmsService;
import com.hdw.websocket.SmsPushSocket;
import lombok.extern.log4j.Log4j2;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;


/**
 * @Description 接收系统消息
 * @Author TuMinglong
 * @Date 2018/5/23 16:05
 */
@Log4j2
@Component
public class SmsReceiveMsgService {

    @Reference
    private ISysSmsService sysSmsService;
    @Reference
    private ISmsTypeService smsTypeService;
    @Reference
    private ISmsRecordService smsRecordService;
    @Autowired
    private SmsPushSocket smsPushSocket;

    @JmsListener(destination = "hdw-dubbo-sms", containerFactory = "queueJmsListenerContainerFactory", concurrency = "5-10")
    public void receiveMsg(String msg) {
        log.info("接收到的数据：" + msg);
        Sms sms = JacksonUtils.toObject(msg, Sms.class);
        if (null != sms) {
            SysSms sysSms = new SysSms();
            sysSms.setSmsTypeId(Long.valueOf(sms.getSmsType()));
            sysSms.setTitle(sms.getTitle());
            sysSms.setContent(sms.getContent());
            sysSms.setSmsTime(sms.getSmsTime());
            sysSms.setIntervalTime(sms.getIntervalTime());
            sysSms.setSmsCount(sms.getSmsCount());
            sysSms.setStatus(0);
            sysSmsService.save(sysSms);
            SmsType smsType = smsTypeService.getById(sms.getSmsType());
            if (null != smsType) {
                List<String> userIds = new ArrayList<>();
                if (smsType.getTargetList().contains(",")) {
                    String[] targetList = smsType.getTargetList().split(",");
                    userIds.addAll(Arrays.asList(targetList));
                } else {
                    userIds.add(smsType.getTargetList());
                }
                //保存消息记录
                userIds.forEach(userId -> {
                    SmsRecord smsRecord = new SmsRecord();
                    smsRecord.setUserId(Long.valueOf(userId));
                    smsRecord.setSmsId(sysSms.getId());
                    smsRecord.setSmsTime(sms.getSmsTime());
                    smsRecord.setStatus(sms.getStatus());
                    smsRecordService.save(smsRecord);
                    sms.setId(smsRecord.getId().toString());
                    //推送消息
                    smsPushSocket.sendInfo(userId, sms.toString());
                });
                //更新消息信息
                sysSms.setRealTime(new Date());
                sysSms.setRealCount(1);
                sysSmsService.updateById(sysSms);
            }
        }
    }
}
