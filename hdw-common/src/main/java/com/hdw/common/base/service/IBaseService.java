package com.hdw.common.base.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.hdw.common.param.QueryParam;
import com.hdw.common.result.PageParam;

import java.util.List;
import java.util.Map;

/**
 * @Description com.hdw.common.base.service
 * @Author TuMingLong
 * @Date 2019/11/7 14:34
 */
public interface IBaseService<T> extends IService<T> {

    /**
     * 自定义分页
     *
     * @param queryParam
     * @return
     */
    PageParam pageList(QueryParam queryParam);

    /**
     * 自定义查询
     *
     * @param queryParam
     * @return
     */
    List<Map<String, Object>> selectMapList(QueryParam queryParam);
}
