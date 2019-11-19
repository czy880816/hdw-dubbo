package com.hdw.common.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Description 自定义异常
 * @Author TuMinglong
 * @Date 2018/12/10 13:59
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class GlobalException extends RuntimeException {
    private Integer errorCode;
    private String message;

    public GlobalException(String message) {
        super(message);
        this.message = message;
    }

    public GlobalException(Integer errorCode, String message) {
        super(message);
        this.errorCode = errorCode;
        this.message = message;
    }
}
