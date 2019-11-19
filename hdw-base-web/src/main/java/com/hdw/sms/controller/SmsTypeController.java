package com.hdw.sms.controller;

import com.hdw.common.result.CommonResult;
import com.hdw.common.result.PageParam;
import com.hdw.common.result.SelectNode;
import com.hdw.sms.entity.SmsType;
import com.hdw.sms.param.SmsTypeParam;
import com.hdw.sms.service.ISmsTypeService;
import com.hdw.system.entity.SysUser;
import com.hdw.system.service.ISysUserService;
import com.hdw.system.shiro.ShiroKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;


/**
 * 消息类型与用户关系表
 *
 * @Author TuMinglong
 * @Date 2019-07-31 16:31:12
 */
@Slf4j
@Api(value = "消息类型与用户关系表接口", tags = {"消息类型与用户关系表接口"})
@RestController
@RequestMapping("sms/smsType")
public class SmsTypeController {
    @Reference
    private ISmsTypeService smsTypeService;

    @Reference
    private ISysUserService userService;

    /**
     * 列表
     */
    @ApiOperation(value = "消息类型与用户关系表列表", notes = "消息类型与用户关系表列表")
    @GetMapping("/list")
    @RequiresPermissions("sms/smsType/list")
    public CommonResult<PageParam<SmsType>> list(SmsTypeParam smsTypeParam) {
        List<SmsType> list = new ArrayList<>();
        PageParam<SmsType> iPage = smsTypeService.selectSmsTypePageList(smsTypeParam);
        List<SmsType> smsTypeList = iPage.getList();
        if (!smsTypeList.isEmpty()) {
            smsTypeList.forEach(smsType -> {
                if (StringUtils.isNotBlank(smsType.getTargetList())) {
                    if (smsType.getTargetList().contains(",")) {
                        String[] targetList = smsType.getTargetList().split(",");
                        smsType.setAccountList(Arrays.asList(targetList));
                        List<SysUser> userList = (List<SysUser>) userService.listByIds(Arrays.asList(targetList));
                        if (!userList.isEmpty()) {
                            StringBuffer sb = new StringBuffer();
                            for (int i = 0; i < userList.size(); i++) {
                                if (i == userList.size() - 1) {
                                    sb.append(userList.get(i).getName());
                                } else {
                                    sb.append(userList.get(i).getName());
                                    sb.append(",");
                                }
                            }
                            smsType.setTargetList(sb.toString());
                        }
                    } else {
                        SysUser user = userService.getById(smsType.getTargetList());
                        if (null != user) {
                            smsType.setTargetList(user.getName());
                            List<String> tempList = new ArrayList<>();
                            tempList.add(user.getId().toString());
                            smsType.setAccountList(tempList);
                        }
                    }
                }
                list.add(smsType);
            });
        }
        iPage.getList().clear();
        iPage.setList(list);
        return CommonResult.ok().data(iPage);
    }


    /**
     * 消息类型与用户关系信息
     */
    @ApiOperation(value = "消息类型与用户关系信息", notes = "消息类型与用户关系信息")
    @ApiImplicitParam(paramType = "path", name = "id", value = "主键ID", dataType = "Integer", required = true)
    @GetMapping("/info/{id}")
    @RequiresPermissions("sms/smsType/info")
    public CommonResult<SmsType> info(@PathVariable("id") Long id) {
        SmsType smsType = smsTypeService.getById(id);
        smsType.setAccountList(Arrays.asList(smsType.getTargetList()));
        return CommonResult.ok().data(smsType);
    }

    /**
     * 保存消息类型与用户关系信息
     */
    @ApiOperation(value = "保存消息类型与用户关系信息", notes = "保存消息类型与用户关系信息")
    @PostMapping("/save")
    @RequiresPermissions("sms/smsType/save")
    public CommonResult save(@Valid @RequestBody SmsType smsType) {
        try {
            smsType.setCreateTime(new Date());
            smsType.setCreateUser(ShiroKit.getUser().getId());
            String targetList = StringUtils.join(smsType.getAccountList(), ",");
            smsType.setTargetList(targetList);
            smsTypeService.save(smsType);
            return CommonResult.ok().msg("添加成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail().msg("运行异常，请联系管理员");
        }
    }

    /**
     * 修改消息类型与用户关系信息
     */
    @ApiOperation(value = "修改消息类型与用户关系信息", notes = "修改消息类型与用户关系信息")
    @PostMapping("/update")
    @RequiresPermissions("sms/smsType/update")
    public CommonResult update(@Valid @RequestBody SmsType smsType) {
        try {
            smsType.setUpdateUser(ShiroKit.getUser().getId());
            smsType.setUpdateTime(new Date());
            String targetList = StringUtils.join(smsType.getAccountList(), ",");
            smsType.setTargetList(targetList);
            smsTypeService.updateById(smsType);
            return CommonResult.ok().msg("修改成功");
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail().msg("运行异常，请联系管理员");
        }

    }

    /**
     * 删除消息类型与用户关系信息
     */
    @ApiOperation(value = "删除消息类型与用户关系信息", notes = "删除消息类型与用户关系信息")
    @ApiImplicitParam(paramType = "query", name = "ids", value = "主键ID数组", dataType = "Integer", required = true, allowMultiple = true)
    @PostMapping("/delete")
    @RequiresPermissions("sms/smsType/delete")
    public CommonResult delete(@RequestBody Long[] ids) {
        try {
            smsTypeService.removeByIds(Arrays.asList(ids));
            return CommonResult.ok().msg("删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.fail().msg("运行异常，请联系管理员");
        }
    }

    /**
     * 获取消息类型树
     *
     * @return
     */
    @ApiOperation(value = "获取消息类型树", notes = "获取消息类型树")
    @GetMapping("/getSmsTypeTree")
    public CommonResult<List<SelectNode>> getSmsTypeTree() {
        try {
            List<SelectNode> nodeList = new ArrayList<>();
            List<SmsType> list = smsTypeService.list();
            list.forEach(baseUser -> {
                SelectNode node = new SelectNode();
                node.setLabel(baseUser.getTypeName());
                node.setValue(baseUser.getId().toString());
                nodeList.add(node);
            });
            return CommonResult.ok().data(nodeList);
        } catch (Exception e) {
            e.printStackTrace();
            return CommonResult.fail().msg("运行异常，请联系管理员");
        }
    }
}
