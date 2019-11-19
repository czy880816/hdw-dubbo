package com.hdw.enterprise.mapper;

import com.hdw.common.base.mapper.SuperMapper;
import com.hdw.enterprise.entity.Enterprise;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

/**
 * 企业信息表
 *
 * @author TuMinglong
 * @date 2018-12-11 13:49:00
 */
public interface EnterpriseMapper extends SuperMapper<Enterprise> {

    /**
     * 多表信息查询
     *
     * @param params
     * @return
     */
    List<Map<String, Object>> selectEnterpriseList(@Param("ew") Map<String, Object> params);

}
