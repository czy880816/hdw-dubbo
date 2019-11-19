package com.hdw.common.exception;

import com.alibaba.fastjson.JSON;
import com.hdw.common.constants.ErrorCode;
import com.hdw.common.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.NoHandlerFoundException;

import javax.validation.ConstraintViolationException;
import javax.validation.ValidationException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @Description 全局异常拦截处理器
 * @Author TuMingLong
 * @Date 2019/11/4 14:06
 */
@Slf4j
//@ControllerAdvice
@RestControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(GlobalException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult handleGlobalException(GlobalException exception) {
        log.error("GlobalException: {}", exception);
        int errorCode;
        if (exception instanceof BusinessException) {
            errorCode = ErrorCode.BUSINESS_EXCEPTION.getCode();
        } else if (exception instanceof DaoException) {
            errorCode = ErrorCode.DAO_EXCEPTION.getCode();
        } else {
            errorCode = ErrorCode.SYSTEM_HANDLE_EXCEPTION.getCode();
        }
        CommonResult resultBody = CommonResult.fail()
                .code(errorCode)
                .msg(exception.getMessage());
        return resultBody;
    }

    /**
     * 登陆授权异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = SysLoginException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult sysLoginExceptionHandler(SysLoginException exception) {
        log.error("SysLoginException: {}", exception);
        CommonResult r = new CommonResult();
        CommonResult resultBody = CommonResult.fail()
                .code(ErrorCode.UNAUTHORIZED.getCode())
                .msg(exception.getMessage());
        return resultBody;
    }

    /**
     * 默认的异常处理
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(Exception.class)
    public CommonResult handleException(Exception exception) {
        log.error("exception: {}", exception);
        CommonResult resultBody = CommonResult.fail()
                .code(ErrorCode.SYSTEM_EXCEPTION.getCode())
                .msg(exception.getMessage());
        return resultBody;
    }


    /**
     * 非法参数验证异常
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.OK)
    public CommonResult handleMethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        log.error("MethodArgumentNotValidException：{}" + ex);
        BindingResult bindingResult = ex.getBindingResult();
        List<String> list = new ArrayList<>();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();
        for (FieldError fieldError : fieldErrors) {
            list.add(fieldError.getDefaultMessage());
        }
        Collections.sort(list);
        log.error("fieldErrors：{}" + JSON.toJSONString(list));

        CommonResult resultBody = CommonResult.fail()
                .code(ErrorCode.PARAMETER_EXCEPTION.getCode())
                .msg(JSON.toJSONString(list));
        return resultBody;
    }

    /**
     * HTTP解析请求参数异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult httpMessageNotReadableException(HttpMessageNotReadableException exception) {
        log.error("httpMessageNotReadableException: {}", exception);
        CommonResult resultBody = CommonResult.fail()
                .code(ErrorCode.PARAMETER_EXCEPTION.getCode())
                .msg(ErrorCode.PARAMETER_EXCEPTION.getMsg());
        return resultBody;
    }

    /**
     * ValidationException
     */
    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult handleValidationException(ValidationException exception) {
        log.error("ValidationException: {}", exception);
        CommonResult resultBody = CommonResult.fail()
                .code(ErrorCode.VALIDATION_EXCEPTION.getCode())
                .msg(exception.getCause().getMessage());
        return resultBody;
    }

    /**
     * HTTP
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(value = HttpMediaTypeException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult httpMediaTypeException(HttpMediaTypeException exception) {
        log.error("httpMediaTypeException: {}", exception);
        CommonResult resultBody = CommonResult.fail()
                .code(ErrorCode.HTTP_MEDIA_TYPE_EXCEPTION.getCode())
                .msg(ErrorCode.HTTP_MEDIA_TYPE_EXCEPTION.getMsg());
        return resultBody;
    }

    /**
     * 约束异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult handleConstraintViolationException(ConstraintViolationException exception) {
        log.error("ConstraintViolationException: {}", exception);
        CommonResult resultBody = CommonResult.fail()
                .code(ErrorCode.CONSTRAINT_VIOLATION_EXCEPTION.getCode())
                .msg(ErrorCode.CONSTRAINT_VIOLATION_EXCEPTION.getMsg());
        return resultBody;
    }

    /**
     * 路径不存在，请检查路径是否正确
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult handlerNoFoundException(NoHandlerFoundException exception) {
        log.error("NoHandlerFoundException: {}", exception);
        CommonResult resultBody = CommonResult.fail()
                .code(ErrorCode.NO_HANDLER_FOUND_EXCEPTION.getCode())
                .msg(ErrorCode.NO_HANDLER_FOUND_EXCEPTION.getMsg());
        return resultBody;
    }

    /**
     * 数据重复，请检查后提交
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(DuplicateKeyException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult handleDuplicateKeyException(DuplicateKeyException exception) {
        log.error("DuplicateKeyException: {}", exception);
        CommonResult resultBody = CommonResult.fail()
                .code(ErrorCode.DUPLICATE_KEY_EXCEPTION.getCode())
                .msg(ErrorCode.DUPLICATE_KEY_EXCEPTION.getMsg());
        return resultBody;
    }

    /**
     * 数据绑定异常
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult handleBindException(BindException exception) {
        log.error("BindException: {}", exception);
        CommonResult resultBody = CommonResult.fail()
                .code(ErrorCode.BIND_EXCEPTION.getCode())
                .msg(ErrorCode.BIND_EXCEPTION.getMsg());
        return resultBody;
    }

    /**
     * 超过最大上传数据
     *
     * @param exception
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    @ResponseStatus(HttpStatus.OK)
    public CommonResult handleMaxUploadSizeExceededException(MaxUploadSizeExceededException exception) {
        log.error("MaxUploadSizeExceededException: {}", exception);
        CommonResult resultBody = CommonResult.fail()
                .code(ErrorCode.MAX_UPLOAD_SIZE_EXCEEDED_EXCEPTION.getCode())
                .msg(ErrorCode.MAX_UPLOAD_SIZE_EXCEEDED_EXCEPTION.getMsg());
        return resultBody;
    }
}
