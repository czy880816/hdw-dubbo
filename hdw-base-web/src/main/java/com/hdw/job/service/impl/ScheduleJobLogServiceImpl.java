package com.hdw.job.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hdw.common.result.PageParam;
import com.hdw.job.entity.ScheduleJobLogEntity;
import com.hdw.job.mapper.ScheduleJobLogMapper;
import com.hdw.job.param.JobLogParam;
import com.hdw.job.service.IScheduleJobLogService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


@Service("scheduleJobLogService")
public class ScheduleJobLogServiceImpl extends ServiceImpl<ScheduleJobLogMapper, ScheduleJobLogEntity> implements IScheduleJobLogService {

    @Override
    public PageParam queryPage(JobLogParam jobLogParam) {
        String jobId = jobLogParam.getJobId();
        QueryWrapper<ScheduleJobLogEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(jobId), "job_id", jobId)
                .orderByDesc("log_id");
        Page page = new Page();
        // 设置当前页码
        page.setCurrent(jobLogParam.getPage());
        // 设置页大小
        page.setSize(jobLogParam.getLimit());
        IPage<ScheduleJobLogEntity> iPage = this.page(page, queryWrapper);
        return new PageParam(iPage);
    }

}
