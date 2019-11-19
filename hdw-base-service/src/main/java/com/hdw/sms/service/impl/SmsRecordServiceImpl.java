package com.hdw.sms.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.google.common.collect.Lists;
import com.hdw.common.base.service.impl.BaseServiceImpl;
import com.hdw.common.result.PageParam;
import com.hdw.sms.entity.SmsRecord;
import com.hdw.sms.mapper.SmsRecordMapper;
import com.hdw.sms.param.SmsRecordParam;
import com.hdw.sms.service.ISmsRecordService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * 消息记录表
 *
 * @Author TuMinglong
 * @Date 2019-07-31 16:31:12
 */
@Slf4j
@Service(interfaceName = "ISmsRecordService")
@Transactional(rollbackFor = Exception.class)
public class SmsRecordServiceImpl extends BaseServiceImpl<SmsRecordMapper, SmsRecord> implements ISmsRecordService {

    public PageParam selectSmsRecordPageList(SmsRecordParam smsRecordParam) {
        QueryWrapper<SmsRecord> wrapper = new QueryWrapper(smsRecordParam);
        wrapper.like(ObjectUtils.isNotEmpty(smsRecordParam.getUserName()), "t3.name", smsRecordParam.getUserName())
                .ge(ObjectUtils.isNotEmpty(smsRecordParam.getStartTime()), "t.push_time", smsRecordParam.getStartTime())
                .le(ObjectUtils.isNotEmpty(smsRecordParam.getEndTime()), "t.push_time", smsRecordParam.getEndTime())
                .eq(ObjectUtils.isNotEmpty(smsRecordParam.getUserId()), "t.user_id", smsRecordParam.getUserId());
        if (ObjectUtils.isNotEmpty(smsRecordParam.getStatus()) && smsRecordParam.getStatus() == -1) {
            wrapper.and(i -> i.ne("t.status", "0").ne("t.status", "3"));
        }
        wrapper.orderByDesc("t.push_time");
        Page page = new Page();
        // 设置当前页码
        page.setCurrent(smsRecordParam.getPage());
        // 设置页大小
        page.setSize(smsRecordParam.getLimit());
        IPage ipage = this.baseMapper.selectSmsRecordPageList(page, wrapper);
        return new PageParam(ipage);
    }


    @Override
    public Integer findUnreadMessagesCount(Long userId) {
        QueryWrapper<SmsRecord> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", userId);
        wrapper.and(i -> i.ne("status", "0").ne("status", "3"));
        wrapper.orderByDesc("push_time");
        return this.count(wrapper);
    }

    @Override
    public List<SmsRecord> findRecent5Messages(Long userId) {
        return this.baseMapper.findRecent5Messages(userId);
    }

    @Override
    public void updateMessageStatus(String[] ids) {
        List<SmsRecord> list = Lists.newArrayList();
        for (String s : ids) {
            SmsRecord smsRecord = new SmsRecord();
            smsRecord.setId(Long.valueOf(s));
            smsRecord.setStatus(3);
            list.add(smsRecord);
        }
        this.updateBatchById(list);
    }
}
