package com.hdw.monitor.exception;

/**
 * @Description Redis连接异常
 * @Author TuMingLong
 * @Date 2019/11/13 15:43
 */
public class RedisConnectException extends Exception {

    private static final long serialVersionUID = 1639374111871115063L;

    public RedisConnectException(String message) {
        super(message);
    }
}
