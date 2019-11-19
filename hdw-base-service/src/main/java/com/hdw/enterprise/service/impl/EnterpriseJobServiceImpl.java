package com.hdw.enterprise.service.impl;


import com.hdw.common.base.service.impl.BaseServiceImpl;
import com.hdw.enterprise.entity.EnterpriseJob;
import com.hdw.enterprise.mapper.EnterpriseJobMapper;
import com.hdw.enterprise.service.IEnterpriseJobService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 企业职务配置表
 *
 * @author TuMinglong
 * @date 2018-12-11 11:36:02
 */
@Slf4j
@Service(interfaceName = "IEnterpriseJobService")
@Transactional(rollbackFor = Exception.class)
public class EnterpriseJobServiceImpl extends BaseServiceImpl<EnterpriseJobMapper, EnterpriseJob> implements IEnterpriseJobService {

    @Override
    public List<EnterpriseJob> selectEnterpriseJobList(Map<String, Object> par) {

        return this.baseMapper.selectEnterpriseJobList(par);
    }
}
