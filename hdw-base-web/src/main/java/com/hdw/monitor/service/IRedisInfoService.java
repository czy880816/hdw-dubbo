package com.hdw.monitor.service;

import com.hdw.monitor.entity.RedisInfo;
import com.hdw.monitor.exception.RedisConnectException;

import java.util.List;
import java.util.Map;

/**
 * @Description com.hdw.monitor.service
 * @Author TuMingLong
 * @Date 2019/11/13 15:42
 */
public interface IRedisInfoService {
    /**
     * 获取 redis 的详细信息
     *
     * @return List
     */
    List<RedisInfo> getRedisInfo() throws RedisConnectException;

    /**
     * 获取 redis key 数量
     *
     * @return Map
     */
    Map<String, Object> getKeysSize() throws RedisConnectException;

    /**
     * 获取 redis 内存信息
     *
     * @return Map
     */
    Map<String, Object> getMemoryInfo() throws RedisConnectException;
}
