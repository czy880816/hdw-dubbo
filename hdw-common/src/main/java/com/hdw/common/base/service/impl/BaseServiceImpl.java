package com.hdw.common.base.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hdw.common.base.mapper.SuperMapper;
import com.hdw.common.base.service.IBaseService;
import com.hdw.common.param.QueryParam;
import com.hdw.common.result.PageParam;
import com.hdw.common.utils.BeanConvertUtils;
import com.hdw.common.utils.JacksonUtils;

import java.util.List;
import java.util.Map;

/**
 * @Description com.hdw.common.base.service.impl
 * @Author TuMingLong
 * @Date 2019/11/7 14:35
 */
public abstract class BaseServiceImpl<M extends SuperMapper<T>, T> extends ServiceImpl<M, T> implements IBaseService<T> {

    public PageParam pageList(QueryParam queryParam) {
        Page page = new Page();
        // 设置当前页码
        page.setCurrent(queryParam.getPage());
        // 设置页大小
        page.setSize(queryParam.getLimit());
        IPage iPage = this.baseMapper.pageList(page, queryParam);
        return new PageParam(iPage);

    }

    public List<Map<String, Object>> selectMapList(QueryParam queryParam) {
        return this.baseMapper.selectMapList(queryParam);
    }


}
