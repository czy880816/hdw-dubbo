package com.hdw.sms.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hdw.common.base.service.impl.BaseServiceImpl;
import com.hdw.common.result.PageParam;
import com.hdw.sms.entity.SysSms;
import com.hdw.sms.mapper.SysSmsMapper;
import com.hdw.sms.param.SmsParam;
import com.hdw.sms.service.ISysSmsService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 消息表
 *
 * @Author TuMinglong
 * @Date 2019-07-31 16:31:12
 */
@Slf4j
@Service(interfaceName = "ISysSmsService")
@Transactional(rollbackFor = Exception.class)
public class SysSmsServiceImpl extends BaseServiceImpl<SysSmsMapper, SysSms> implements ISysSmsService {

    public PageParam selectSmsPageList(SmsParam smsParam) {
        QueryWrapper<SysSms> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda()
                .like(ObjectUtils.isNotEmpty(smsParam.getTitle()), SysSms::getTitle, smsParam.getTitle())
                .eq(ObjectUtils.isNotEmpty(smsParam.getSmsTypeId()), SysSms::getSmsTypeId, smsParam.getSmsTypeId());
        queryWrapper.like(ObjectUtils.isNotEmpty(smsParam.getTypeName()), "t2.type_name", smsParam.getTypeName());
        queryWrapper.orderByDesc("create_time");
        Page page = new Page();
        // 设置当前页码
        page.setCurrent(smsParam.getPage());
        // 设置页大小
        page.setSize(smsParam.getLimit());
        IPage ipage = this.baseMapper.selectSmsPageList(page, queryWrapper);
        return new PageParam(ipage);
    }

    @Override
    public List<SysSms> selectCurrentSmsList() {
        return this.baseMapper.selectCurrentSmsList();
    }
}
