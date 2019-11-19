package com.hdw.common.exception;

/**
 * @Description DAO异常
 * @Author TuMingLong
 * @Date 2019/11/1 16:49
 */
public class DaoException extends GlobalException {
    public DaoException(String message) {
        super(message);
    }

    public DaoException(Integer errorCode, String message) {
        super(errorCode, message);
    }
}
