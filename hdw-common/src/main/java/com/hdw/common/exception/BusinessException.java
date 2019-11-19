package com.hdw.common.exception;

/**
 * @Description 业务异常
 * @Author TuMingLong
 * @Date 2019/11/1 16:48
 */
public class BusinessException extends GlobalException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
