package com.hdw.system.service;

import com.hdw.common.base.service.IBaseService;
import com.hdw.system.entity.SysFile;

import java.util.List;
import java.util.Map;

/**
 * 附件表
 *
 * @author TuMinglong
 * @date 2018-12-11 11:35:15
 */
public interface ISysFileService extends IBaseService<SysFile> {

    /**
     * 通过tableId和recordId获取相关附件信息
     *
     * @param params
     * @return
     */
    List<SysFile> selectFileListByTableIdAndRecordId(Map<String, Object> params);

}

