package com.hdw.job.controller;

import com.hdw.common.result.CommonResult;
import com.hdw.common.result.PageParam;
import com.hdw.job.entity.ScheduleJobLogEntity;
import com.hdw.job.param.JobLogParam;
import com.hdw.job.service.IScheduleJobLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * @Description 定时任务日志
 * @Author TuMinglong
 * @Date 2019/1/18 15:59
 **/
@Api(value = "定时任务日志接口", tags = {"定时任务日志接口"})
@RestController
@RequestMapping("/sys/scheduleLog")
public class ScheduleJobLogController {
    @Autowired
    private IScheduleJobLogService scheduleJobLogService;

    /**
     * 定时任务日志列表
     */
    @ApiOperation(value = "定时任务日志列表", notes = "定时任务日志列表")
    @GetMapping("/list")
    @RequiresPermissions("sys/schedule/log")
    public CommonResult<PageParam<ScheduleJobLogEntity>> list(JobLogParam jobLogParam) {
        PageParam<ScheduleJobLogEntity> page = scheduleJobLogService.queryPage(jobLogParam);

        return CommonResult.ok().data(page);
    }

    /**
     * 定时任务日志信息
     */
    @ApiOperation(value = "定时任务日志信息", notes = "定时任务日志信息")
    @ApiImplicitParam(paramType = "path", name = "logId", value = "主键ID", dataType = "Integer", required = true)
    @GetMapping("/info/{logId}")
    public CommonResult<ScheduleJobLogEntity> info(@PathVariable("logId") Long logId) {
        ScheduleJobLogEntity log = scheduleJobLogService.getById(logId);

        return CommonResult.ok().data(log);
    }
}
