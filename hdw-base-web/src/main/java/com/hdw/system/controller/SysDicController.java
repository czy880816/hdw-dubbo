package com.hdw.system.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.google.common.collect.Maps;
import com.hdw.common.result.CommonResult;
import com.hdw.common.result.SelectNode;
import com.hdw.common.result.SelectTreeNode;
import com.hdw.enterprise.service.IEnterpriseService;
import com.hdw.system.entity.SysDic;
import com.hdw.system.entity.vo.DicVo;
import com.hdw.system.service.ISysDicService;
import com.hdw.system.shiro.ShiroKit;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.compress.utils.Lists;
import org.apache.commons.lang3.StringUtils;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;

/**
 * @author TuMinglong
 * @description 数据字典接口
 * @date 2018年3月6日 上午10:02:48
 */
@Slf4j
@Api(value = "数据字典接口", tags = {"数据字典接口"})
@RestController
@RequestMapping("/sys/dic")
public class SysDicController {

    @Reference
    private ISysDicService sysDicService;

    @Reference
    private IEnterpriseService enterpriseService;

    /**
     * 数据字典树表
     *
     * @return
     */
    @ApiOperation(value = "日志列表", notes = "日志列表")
    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "query", name = "dicName", value = "名称", required = false, dataType = "String"),
            @ApiImplicitParam(paramType = "query", name = "dicCode", value = "编码", required = false, dataType = "String")
    })
    @GetMapping("/list")
    public CommonResult<DicVo> treeGrid(@RequestParam(required = false) String dicName,
                                        @RequestParam(required = false) String dicCode) {
        Map<String, Object> par = new HashMap<>();
        if (StringUtils.isNotBlank(dicName)) {
            par.put("varName", StringUtils.deleteWhitespace(dicName));
        }
        if (StringUtils.isNotBlank(dicCode)) {
            par.put("varCode", StringUtils.deleteWhitespace(dicCode));
        }
        return CommonResult.ok().data(sysDicService.selectTreeGrid(par));

    }

    /**
     * 数据字典选择
     *
     * @return
     */
    @ApiOperation(value = "数据字典选择", notes = "数据字典选择")
    @ApiImplicitParam(paramType = "path", name = "parentId", value = "父ID", required = true, dataType = "Integer")
    @GetMapping("/select/{parentId}")
    public CommonResult<List<SelectTreeNode>> select(@PathVariable("parentId") Long parentId) {
        Map<String, Object> params = Maps.newHashMap();
        if (parentId != null && 0 != parentId) {
            params.put("parentId", parentId);
        }
        List<SysDic> dicList = sysDicService.selectDicList(params);
        List<SelectTreeNode> treeNodeList = Lists.newArrayList();
        if (!dicList.isEmpty()) {
            dicList.forEach(dic -> {
                SelectTreeNode selectTreeNode = new SelectTreeNode();
                selectTreeNode.setId(dic.getId().toString());
                selectTreeNode.setParentId(dic.getParentId().toString());
                selectTreeNode.setName(dic.getVarName());
                treeNodeList.add(selectTreeNode);
            });
        }
        treeNodeList.add(SelectTreeNode.createParent());
        return CommonResult.ok().data(treeNodeList);
    }

    /**
     * 字典信息
     *
     * @param dicId
     * @return
     */
    @ApiOperation(value = "字典信息", notes = "字典信息")
    @ApiImplicitParam(paramType = "path", name = "dicId", value = "主键ID", required = true, dataType = "Integer")
    @GetMapping("/info/{dicId}")
    public CommonResult<SysDic> info(@PathVariable("dicId") Long dicId) {
        SysDic sysDic = sysDicService.getById(dicId);
        SysDic sysDic2 = sysDicService.getById(sysDic.getParentId());
        if (sysDic2 != null) {
            sysDic.setParentName(sysDic.getVarName());
        } else {
            sysDic.setParentName("顶级");
        }
        return CommonResult.ok().data(sysDic);
    }

    /**
     * 添加字典信息
     *
     * @param
     * @return
     */
    @ApiOperation(value = "添加字典信息", notes = "添加字典信息")
    @PostMapping("/save")
    public CommonResult save(@Valid @RequestBody SysDic sysDic) {
        try {
            sysDic.setCreateTime(new Date());
            sysDic.setCreateUser(ShiroKit.getUser().getLoginName());
            boolean b = sysDicService.save(sysDic);
            if (b) {
                return CommonResult.ok().msg("添加成功！");
            } else {
                return CommonResult.fail().msg("添加失败！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail().msg("添加失败，请联系管理员");
        }

    }

    /**
     * 修改字典信息
     *
     * @param sysDic
     * @return
     */
    @ApiOperation(value = "修改字典信息", notes = "修改字典信息")
    @PostMapping("/update")
    public CommonResult update(@Valid @RequestBody SysDic sysDic) {
        try {
            sysDic.setUpdateTime(new Date());
            sysDic.setUpdateUser(ShiroKit.getUser().getLoginName());
            boolean b = sysDicService.updateById(sysDic);
            if (b) {
                return CommonResult.ok().msg("修改成功！");
            } else {
                return CommonResult.fail().msg("修改失败！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail().msg("编辑失败，请联系管理员");
        }
    }

    /**
     * 删除字典信息
     *
     * @param dicId
     * @return
     */
    @ApiOperation(value = "删除字典信息", notes = "删除字典信息")
    @ApiImplicitParam(paramType = "path", name = "roleId", value = "主键ID", dataType = "Integer", required = true)
    @PostMapping("/delete/{dicId}")
    public CommonResult delete(@PathVariable("dicId") Long dicId) {
        try {
            sysDicService.removeById(dicId);
            QueryWrapper<SysDic> wrapper = new QueryWrapper<>();
            wrapper.eq("parent_id", dicId);
            sysDicService.remove(wrapper);
            return CommonResult.ok().msg("删除成功！");
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail().msg("批量删除失败，请联系管理员");
        }
    }

    /**
     * 批量删除字典信息
     *
     * @param ids
     * @return
     */
    @ApiOperation(value = "批量删除字典信息", notes = "批量删除字典信息")
    @ApiImplicitParam(paramType = "query", name = "roleIds", value = "字典ID数组", dataType = "Integer", required = true, allowMultiple = true)
    @PostMapping("/delete")
    public CommonResult deleteBatchIds(@RequestParam Long[] ids) {
        try {
            List<Long> idList = new ArrayList<Long>();
            Collections.addAll(idList, ids);
            if (idList != null && !idList.isEmpty()) {
                sysDicService.removeByIds(Arrays.asList(ids));
                for (Long id : idList) {
                    QueryWrapper<SysDic> wrapper = new QueryWrapper<>();
                    wrapper.eq("parent_id", id);
                    sysDicService.remove(wrapper);
                }
                return CommonResult.ok().msg("删除成功！");
            } else {
                return CommonResult.fail().msg("删除失败！");
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            return CommonResult.fail().msg("批量删除失败，请联系管理员");
        }
    }

    /**
     * 数据字典select树
     *
     * @return
     */
    @ApiOperation(value = "数据字典select树", notes = "数据字典select树")
    @ApiImplicitParam(paramType = "path", name = "parentId", value = "父ID", dataType = "Integer", required = true)
    @GetMapping("/selectNode/{parentId}")
    public CommonResult<List<SelectNode>> selectTree(@PathVariable("parentId") Long parentId) {
        List<SelectNode> tree = new ArrayList<>();
        Map<String, Object> par = new HashMap<>();
        par.put("parentId", parentId);
        List<Map<String, Object>> list = sysDicService.selectTreeByParentId(par);
        if (!list.isEmpty()) {
            for (Map<String, Object> map : list) {
                SelectNode selectNode = new SelectNode();
                selectNode.setLabel(map.get("varName").toString());
                selectNode.setValue(map.get("id").toString());
                tree.add(selectNode);
            }
        }
        return CommonResult.ok().data(tree);
    }
}
