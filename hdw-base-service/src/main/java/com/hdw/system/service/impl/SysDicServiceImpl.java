package com.hdw.system.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.hdw.system.entity.SysDic;
import com.hdw.system.entity.vo.DicVo;
import com.hdw.system.mapper.SysDicMapper;
import com.hdw.system.service.ISysDicService;
import lombok.extern.slf4j.Slf4j;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 数据字典 服务实现类
 * </p>
 *
 * @author TuMinglong
 * @since 2018-04-26
 */
@Slf4j
@Service(interfaceName = "ISysDicService")
@Transactional(rollbackFor = Exception.class)
public class SysDicServiceImpl extends ServiceImpl<SysDicMapper, SysDic> implements ISysDicService {

    @Override
    public List<DicVo> selectTreeGrid(Map<String, Object> par) {

        return this.baseMapper.selectTreeGrid(par);
    }

    @Override
    public List<SysDic> selectDicList(Map<String, Object> params) {
        return this.baseMapper.selectDicList(params);
    }


    @Override
    public List<Map<String, Object>> selectTreeByParentId(Map<String, Object> par) {

        return this.baseMapper.selectTreeByParentId(par);
    }
}
