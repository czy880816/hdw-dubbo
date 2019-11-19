package com.hdw.job.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.hdw.common.result.PageParam;
import com.hdw.job.entity.ScheduleJobLogEntity;
import com.hdw.job.param.JobLogParam;


/**
 * @Description 定时任务日志
 * @Author TuMinglong
 * @Date 2019/1/18 15:59
 **/
public interface IScheduleJobLogService extends IService<ScheduleJobLogEntity> {

    PageParam queryPage(JobLogParam jobLogParam);

}
