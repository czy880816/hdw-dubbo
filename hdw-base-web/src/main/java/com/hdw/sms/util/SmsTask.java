package com.hdw.sms.util;

import com.google.common.collect.Lists;
import com.hdw.sms.entity.*;
import com.hdw.sms.service.ISmsRecordService;
import com.hdw.sms.service.ISmsTypeService;
import com.hdw.sms.service.ISysSmsService;
import com.hdw.system.entity.SysUser;
import com.hdw.system.service.ISysUserService;
import com.hdw.websocket.HomeSmsPushSocket;
import com.hdw.websocket.SmsPushSocket;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

/**
 * @Description 推送任务
 * @Author TuMinglong
 * @Date 2019/8/1 14:05
 */
@Component
public class SmsTask {

    @Reference
    private ISmsRecordService smsRecordService;

    @Reference
    private ISysSmsService smsService;

    @Reference
    private ISmsTypeService smsTypeService;

    @Reference
    private ISysUserService userService;

    @Autowired
    private SmsPushSocket smsPushSocket;

    @Autowired
    private HomeSmsPushSocket homeSmsPushSocket;

    /**
     * 推送实时页面消息
     *
     * @return
     */
    public void pushPageMsg() {
        try {
            List<SysSms> list = new ArrayList<>();
//            Long[] array = new Long[]{1l, 2l};
//            List<Long> smsTypeIds = new ArrayList<>();
//            smsTypeIds.addAll(Arrays.asList(array));
//            QueryWrapper<SysSms> wrapper = new QueryWrapper<>();
//            wrapper.lambda()
//                    .eq(SysSms::getStatus, "0")
//                    .isNotNull(SysSms::getSmsCount)
//                    .isNotNull(SysSms::getIntervalTime)
//                    .notIn(SysSms::getSmsTypeId, smsTypeIds)
//                    .le(SysSms::getSmsTime, DateUtils.formatDate(new Date(), "yyyy-MM-dd HH:mm:ss"));
            List<SysSms> baseSysSmsList = smsService.selectCurrentSmsList();
            if (!baseSysSmsList.isEmpty()) {
                baseSysSmsList.forEach(baseSms -> {
                    if (null != baseSms.getRealCount() && null != baseSms.getRealTime()) {
                        /* 先转成毫秒并求差 */
                        Long m = new Date().getTime() - baseSms.getRealTime().getTime();
                        if (baseSms.getRealCount() < baseSms.getSmsCount() && (m > baseSms.getIntervalTime() * 1000)) {
                            list.add(baseSms);
                        }
                    } else {
                        list.add(baseSms);
                    }
                });
            }
            if (!list.isEmpty()) {
                saveSmsRecordAndPushSocket(list);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 保存消息记录并向socket推送消息
     *
     * @param list
     */
    private void saveSmsRecordAndPushSocket(List<SysSms> list) {
        if (!list.isEmpty()) {
            for (SysSms baseSysSms : list) {
                SmsType smsType = smsTypeService.getById(baseSysSms.getSmsTypeId());
                if (null != smsType) {
                    List<String> userIds = Lists.newArrayList();
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
                        smsRecord.setSmsId(baseSysSms.getId());
                        smsRecord.setSmsTime(new Date());
                        smsRecord.setStatus(0);
                        smsRecordService.save(smsRecord);
                        Sms sms = new Sms();
                        sms.setId(smsRecord.getId().toString());
                        sms.setTitle(baseSysSms.getTitle());
                        sms.setContent(baseSysSms.getContent());
                        //推送消息
                        smsPushSocket.sendInfo(userId, sms.toString());
                    });
                    //更新消息信息
                    baseSysSms.setRealTime(new Date());
                    if (baseSysSms.getRealCount() != null) {
                        baseSysSms.setRealCount(baseSysSms.getRealCount() + 1);
                    } else {
                        baseSysSms.setRealCount(1);
                    }
                    smsService.updateById(baseSysSms);
                }
            }
        }
    }


    /**
     * 推送首页最近5条消息及未读消息总数
     */
    public void pushHomeUnreadMessage() {
        try {
            List<SysUser> userList = userService.list();
            if (!userList.isEmpty()) {
                userList.forEach(user -> {
                    Integer count = smsRecordService.findUnreadMessagesCount(user.getId());
                    List<SmsRecord> smsRecordList = smsRecordService.findRecent5Messages(user.getId());
                    UnreadSms unreadSms = new UnreadSms();
                    unreadSms.setCount(count);
                    List<Sms> smsList = new ArrayList<>();
                    if (!smsRecordList.isEmpty()) {
                        smsRecordList.forEach(smsRecord -> {
                            Sms sms = new Sms();
                            sms.setId(smsRecord.getId().toString());
                            sms.setTitle(smsRecord.getTitle());
                            sms.setContent(smsRecord.getContent());
                            sms.setTimeStr(smsRecord.getTimeStr());
                            sms.setStatus(smsRecord.getStatus());
                            smsList.add(sms);
                        });
                    }
                    unreadSms.setList(smsList);
                    homeSmsPushSocket.sendInfo(user.getId().toString(), unreadSms.toString());
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
