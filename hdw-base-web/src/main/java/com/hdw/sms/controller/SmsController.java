package com.hdw.sms.controller;

import com.hdw.common.result.CommonResult;
import com.hdw.common.result.PageParam;
import com.hdw.sms.entity.SysSms;
import com.hdw.sms.param.SmsParam;
import com.hdw.sms.service.ISysSmsService;
import com.hdw.system.shiro.ShiroKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.Date;


/**
 * 消息表
 *
 * @Author TuMinglong
 * @Date 2019-07-31 16:31:12
 */
@Api(value = "消息表接口", tags = {"消息表接口"})
@RestController
@RequestMapping("sms/sms")
public class SmsController {
    @Reference
    private ISysSmsService smsService;

    /**
     * 列表
     */
    @ApiOperation(value = "消息表列表", notes = "消息表列表")
    @GetMapping("/list")
    @RequiresPermissions("sms/sms/list")
    public CommonResult<PageParam<SysSms>> list(SmsParam smsParam) {
        PageParam<SysSms> page = smsService.selectSmsPageList(smsParam);
        return CommonResult.ok().data(page);
    }


    /**
     * 消息信息
     */
    @ApiOperation(value = "消息信息", notes = "消息信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", dataType = "Integer", required = true)
    @GetMapping("/info/{id}")
    @RequiresPermissions("sms/sms/info")
    public CommonResult<SysSms> info(@PathVariable("id") Long id) {
        SysSms sysSms = smsService.getById(id);
        return CommonResult.ok().data(sysSms);
    }

    /**
     * 保存消息信息
     */
    @ApiOperation(value = "保存消息信息", notes = "保存消息信息")
    @PostMapping("/save")
    @RequiresPermissions("sms/sms/save")
    public CommonResult save(@Valid @RequestBody SysSms sysSms) {
        try {
            sysSms.setCreateTime(new Date());
            sysSms.setCreateUser(ShiroKit.getUser().getId());
            sysSms.setUpdateTime(new Date());
            sysSms.setUpdateUser(ShiroKit.getUser().getId());
            smsService.save(sysSms);
            return CommonResult.ok().msg("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.fail().msg("运行异常，请联系管理员");
        }
    }

    /**
     * 修改消息信息
     */
    @ApiOperation(value = "修改消息信息", notes = "修改消息信息")
    @PostMapping("/update")
    @RequiresPermissions("sms/sms/update")
    public CommonResult update(@Valid @RequestBody SysSms sysSms) {
        try {
            sysSms.setUpdateUser(ShiroKit.getUser().getId());
            sysSms.setUpdateTime(new Date());
            smsService.updateById(sysSms);
            return CommonResult.ok().msg("修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.fail().msg("运行异常，请联系管理员");
        }

    }

    /**
     * 删除消息信息
     */
    @ApiOperation(value = "删除消息信息", notes = "删除消息信息")
    @ApiImplicitParam(paramType = "query", name = "ids", value = "主键ID数组", dataType = "Integer", required = true, allowMultiple = true)
    @PostMapping("/delete")
    @RequiresPermissions("sms/sms/delete")
    public CommonResult delete(@RequestBody Long[] ids) {
        try {
            smsService.removeByIds(Arrays.asList(ids));
            return CommonResult.ok().msg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.fail().msg("运行异常，请联系管理员");
        }
    }

}
