package com.hdw.enterprise.service;


import com.hdw.common.base.service.IBaseService;
import com.hdw.enterprise.entity.Enterprise;

import java.util.List;
import java.util.Map;

/**
 * 企业信息表
 *
 * @author TuMinglong
 * @date 2018-12-11 13:49:00
 */
public interface IEnterpriseService extends IBaseService<Enterprise> {

    /**
     * 多表信息查询
     *
     * @param par
     * @return
     */
    List<Map<String, Object>> selectEnterpriseList(Map<String, Object> par);


}

