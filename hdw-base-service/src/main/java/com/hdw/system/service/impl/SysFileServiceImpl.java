package com.hdw.system.service.impl;


import com.hdw.common.base.service.impl.BaseServiceImpl;
import com.hdw.system.entity.SysFile;
import com.hdw.system.mapper.SysFileMapper;
import com.hdw.system.service.ISysFileService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * 附件表
 *
 * @author TuMinglong
 * @date 2018-12-11 11:35:15
 */
@Slf4j
@Service(interfaceName = "ISysFileService")
@Transactional(rollbackFor = Exception.class)
public class SysFileServiceImpl extends BaseServiceImpl<SysFileMapper, SysFile> implements ISysFileService {

    @Override
    public List<SysFile> selectFileListByTableIdAndRecordId(Map<String, Object> params) {
        return this.baseMapper.selectFileListByTableIdAndRecordId(params);
    }
}
