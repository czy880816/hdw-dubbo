package com.hdw.common.exception;

/**
 * @Description 系统登录异常
 * @Author TuMingLong
 * @Date 2019/11/12 11:49
 */
public class SysLoginException extends GlobalException {

    public SysLoginException(String message) {
        super(message);
    }

    public SysLoginException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
