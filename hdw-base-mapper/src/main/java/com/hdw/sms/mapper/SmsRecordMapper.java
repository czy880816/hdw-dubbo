package com.hdw.sms.mapper;


import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.hdw.common.base.mapper.SuperMapper;
import com.hdw.sms.entity.SmsRecord;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * @Description 消息记录表
 * @Author TuMinglong
 * @Date 2019-07-31 16:31:12
 */
public interface SmsRecordMapper extends SuperMapper<SmsRecord> {

    /**
     * 自定义分页
     *
     * @param page
     * @param queryWrapper
     * @return
     */
    IPage<SmsRecord> selectSmsRecordPageList(Page<SmsRecord> page, @Param("ew") Wrapper<SmsRecord> queryWrapper);


    /**
     * 最近5条未读消息
     *
     * @param userId
     * @return
     */
    List<SmsRecord> findRecent5Messages(@Param("userId") Long userId);

}
