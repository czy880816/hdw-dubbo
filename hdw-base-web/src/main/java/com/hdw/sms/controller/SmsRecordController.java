package com.hdw.sms.controller;

import com.hdw.common.result.CommonResult;
import com.hdw.common.result.PageParam;
import com.hdw.sms.entity.SmsRecord;
import com.hdw.sms.param.SmsRecordParam;
import com.hdw.sms.service.ISmsRecordService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 消息记录表
 *
 * @Author TuMinglong
 * @Date 2019-07-31 16:31:12
 */
@Slf4j
@Api(value = "消息记录接口", tags = {"消息记录接口"})
@RestController
@RequestMapping("sms/smsRecord")
public class SmsRecordController {
    @Reference
    private ISmsRecordService smsRecordService;

    /**
     * 列表
     */
    @ApiOperation(value = "消息记录列表", notes = "消息记录列表")
    @GetMapping("/list")
    @RequiresPermissions("sms/smsRecord/list")
    public CommonResult<PageParam<SmsRecord>> list(SmsRecordParam smsRecordParam) {
        PageParam<SmsRecord> page = smsRecordService.selectSmsRecordPageList(smsRecordParam);
        return CommonResult.ok().data(page);
    }

    /**
     * 删除消息记录信息
     */
    @ApiOperation(value = "删除消息记录信息", notes = "删除消息记录信息")
    @ApiImplicitParam(paramType = "query", name = "ids", value = "主键ID数据", dataType = "Integer", required = true, allowMultiple = true)
    @PostMapping("/delete")
    @RequiresPermissions("sms/smsRecord/delete")
    public CommonResult delete(@RequestBody Long[] ids) {
        try {
            smsRecordService.removeByIds(Arrays.asList(ids));
            return CommonResult.ok().msg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.fail().msg("运行异常，请联系管理员");
        }
    }

    /**
     * 更新消息记录状态已读
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "更新消息记录状态已读", notes = "更新消息记录状态已读")
    @ApiImplicitParam(paramType = "query", name = "ids", value = "主键ID数据", dataType = "String", required = true, allowMultiple = true)
    @PostMapping("/updateStatus")
    public CommonResult updateStatus(@RequestBody String[] ids) {
        try {
            smsRecordService.updateMessageStatus(ids);
            return CommonResult.ok().msg("更新状态成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.fail().msg("运行异常，请联系管理员");
        }
    }
}
