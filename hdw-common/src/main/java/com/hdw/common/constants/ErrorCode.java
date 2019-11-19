package com.hdw.common.constants;

/**
 * @Description 自定义返回码
 * @Author TuMingLong
 * @Date 2019/11/1 11:14
 */
public enum ErrorCode {
    /**
     * 操作成功
     */
    SUCCESS(0, "success"),
    /**
     * 非法访问
     */
    UNAUTHORIZED(401, "unauthorized"),
    /**
     * 没有权限
     */
    NOT_PERMISSION(403, "not_permission"),
    /**
     * 请求的资源不存在
     */
    NOT_FOUND(404, "not_found"),
    /**
     * 操作失败
     */
    FAIL(500, "fail"),
    /**
     * 登录失败
     */
    LOGIN_EXCEPTION(4000, "login_exception"),
    /**
     * 系统异常
     */
    SYSTEM_EXCEPTION(5000, "system_exception"),
    /**
     * 请求参数校验异常
     */
    PARAMETER_EXCEPTION(5001, "parameter_exception"),
    /**
     * 请求参数解析异常
     */
    PARAMETER_PARSE_EXCEPTION(5002, "parameter_parse_exception"),
    /**
     * 请求参数校验异常
     */
    VALIDATION_EXCEPTION(5003, "validation_exception"),

    /**
     * 请求参数校验异常
     */
    CONSTRAINT_VIOLATION_EXCEPTION(5004, "constraint_violation_exception"),
    /**
     * HTTP Media 类型异常
     */
    HTTP_MEDIA_TYPE_EXCEPTION(5005, "http_media_type_exception"),
    /**
     * 路径不存在，请检查路径是否正确
     */
    NO_HANDLER_FOUND_EXCEPTION(5006, "no_handler_found_exception"),

    /**
     * 数据重复，请检查后提交
     */
    DUPLICATE_KEY_EXCEPTION(5007, "duplicate_key_exception"),

    /**
     * 数据绑定异常
     */
    BIND_EXCEPTION(5008, "bind_exception"),
    /**
     * 超过最大上传数量
     */
    MAX_UPLOAD_SIZE_EXCEEDED_EXCEPTION(5009, "max_upload_size_exceeded_exception"),
    /**
     * 系统处理异常
     */
    SYSTEM_HANDLE_EXCEPTION(5100, "system_handle_exception"),

    /**
     * 业务处理异常
     */
    BUSINESS_EXCEPTION(5101, "business_exception"),

    /**
     * 数据库处理异常
     */
    DAO_EXCEPTION(5102, "dao_exception"),

    /**
     * 验证码校验异常
     */
    VERIFICATION_CODE_EXCEPTION(5103, "verification_code_exception"),

    /**
     * 登陆授权异常
     */
    AUTHENTICATION_EXCEPTION(5104, "authentication_exception"),

    /**
     * 登陆授权异常
     */
    UNAUTHENTICATED_EXCEPTION(5105, "unauthenticated_exception"),

    /**
     * 没有访问权限
     */
    UNAUTHORIZED_EXCEPTION(5106, "unauthorized_exception"),

    ;


    private final int code;
    private final String msg;

    ErrorCode(final int code, final String msg) {
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static ErrorCode getHttpCode(int code) {
        ErrorCode[] ecs = ErrorCode.values();
        for (ErrorCode ec : ecs) {
            if (ec.getCode() == code) {
                return ec;
            }
        }
        return SUCCESS;
    }
}
