package com.hdw.monitor.service.impl;

import com.hdw.monitor.entity.RedisInfo;
import com.hdw.monitor.exception.RedisConnectException;
import com.hdw.monitor.service.IRedisInfoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Description Redis 监控信息获取
 * @Author TuMingLong
 * @Date 2019/11/13 15:42
 */
@Slf4j
@Service
public class RedisInfoServiceImpl implements IRedisInfoService {

    @Resource
    private RedisConnectionFactory redisConnectionFactory;

    /**
     * Redis详细信息
     */
    @Override
    public List<RedisInfo> getRedisInfo() throws RedisConnectException {
        Properties info = redisConnectionFactory.getConnection().info();
        List<RedisInfo> infoList = new ArrayList<>();
        RedisInfo redisInfo = null;
        for (Map.Entry<Object, Object> entry : info.entrySet()) {
            redisInfo = new RedisInfo();
            redisInfo.setKey(getString(entry.getKey()));
            redisInfo.setValue(getString(entry.getValue()));
            infoList.add(redisInfo);
        }
        return infoList;
    }

    @Override
    public Map<String, Object> getKeysSize() throws RedisConnectException {
        Long dbSize = redisConnectionFactory.getConnection().dbSize();
        Map<String, Object> map = new HashMap<>();
        map.put("create_time", System.currentTimeMillis());
        map.put("dbSize", dbSize);

        log.info("--getKeysSize--: " + map.toString());
        return map;
    }

    @Override
    public Map<String, Object> getMemoryInfo() throws RedisConnectException {
        Map<String, Object> map = null;
        Properties info = redisConnectionFactory.getConnection().info();
        for (Map.Entry<Object, Object> entry : info.entrySet()) {
            String key =getString(entry.getKey());
            if ("used_memory".equals(key)) {
                map = new HashMap<>();
                map.put("used_memory", entry.getValue());
                map.put("create_time", System.currentTimeMillis());
            }
        }
        log.info("--getMemoryInfo--: " + map.toString());
        return map;
    }

    private  String getString(Object object) {
        if (isEmpty(object)) {
            return "";
        }
        return (object.toString().trim());
    }

    private boolean isEmpty(Object object) {
        if (object == null) {
            return (true);
        }
        if ("".equals(object)) {
            return (true);
        }
        if ("null".equals(object)) {
            return (true);
        }
        return (false);
    }
}
