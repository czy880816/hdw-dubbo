package com.hdw.common.utils;

import java.util.UUID;


/**
 * @Description 生成UUId或随机数字
 * @Author TuMinglong
 * @Date 2018/12/10 16:48
 */
public class UUIDGenerator {
    /**
     * 获取32位随机字符串
     *
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();
    }

}
