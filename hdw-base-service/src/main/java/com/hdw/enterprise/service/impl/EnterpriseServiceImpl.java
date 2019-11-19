package com.hdw.enterprise.service.impl;


import com.hdw.common.base.service.impl.BaseServiceImpl;
import com.hdw.enterprise.entity.Enterprise;
import com.hdw.enterprise.mapper.EnterpriseMapper;
import com.hdw.enterprise.service.IEnterpriseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 企业信息表
 *
 * @author TuMinglong
 * @date 2018-12-11 13:49:00
 */
@Slf4j
@Service(interfaceName = "IEnterpriseService")
@Transactional(rollbackFor = Exception.class)
public class EnterpriseServiceImpl extends BaseServiceImpl<EnterpriseMapper, Enterprise> implements IEnterpriseService {

    @Override
    public List<Map<String, Object>> selectEnterpriseList(Map<String, Object> par) {
        return this.baseMapper.selectEnterpriseList(par);
    }

}
