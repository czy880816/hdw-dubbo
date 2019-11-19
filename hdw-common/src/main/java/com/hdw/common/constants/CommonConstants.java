package com.hdw.common.constants;

/**
 * @Description 公共常量
 * @Author TuMinglong
 * @Date 2019/5/10 13:59
 **/
public class CommonConstants {
    /**
     * 自定义错误
     */
    public static final String X_ERROR = "x.servlet.exception.code";
    public static final String X_ERROR_CODE = "x.servlet.exception.error";
    public static final String X_ERROR_MESSAGE = "x.servlet.exception.message";
    public static final String X_ACCESS_DENIED = "x.access.denied";

    /**
     * 超级管理员ID
     */
    public static final int SUPER_ADMIN = 1;

    /**
     * 客户端ID KEY
     */
    public static final String SIGN_CLIENT_ID_KEY = "clientId";

    /**
     * 客户端秘钥 KEY
     */
    public static final String SIGN_CLIENT_SECRET_KEY = "clientSecret";

    /**
     * 随机字符串 KEY
     */
    public static final String SIGN_NONCE_KEY = "nonce";
    /**
     * 时间戳 KEY
     */
    public static final String SIGN_TIMESTAMP_KEY = "timestamp";
    /**
     * 签名类型 KEY
     */
    public static final String SIGN_SIGN_TYPE_KEY = "signType";
    /**
     * 签名结果 KEY
     */
    public static final String SIGN_SIGN_KEY = "sign";


    /**
     * 默认最小页码
     */
    public static final long MIN_PAGE = 0;
    /**
     * 最大显示条数
     */
    public static final long MAX_LIMIT = 1000;
    /**
     * 默认页码
     */
    public static final long DEFAULT_PAGE = 1;
    /**
     * 默认显示条数
     */
    public static final long DEFAULT_LIMIT = 10;
    /**
     * 页码 KEY
     */
    public static final String PAGE_KEY = "page";
    /**
     * 显示条数 KEY
     */
    public static final String PAGE_LIMIT_KEY = "limit";
    /**
     * 排序字段 KEY
     */
    public static final String PAGE_SORT_KEY = "sort";
    /**
     * 排序方向 KEY
     */
    public static final String PAGE_ORDER_KEY = "order";


    /**
     * JWT用户名
     */
    public static final String JWT_DEFAULT_USERNAME = "username";


    /**
     * 登陆Token
     */
    public static final String JWT_DEFAULT_TOKEN_NAME = "token";

    /**
     * JWT Token默认密钥
     */
    public static final String JWT_DEFAULT_SECRET = "666666";

    /**
     * 签发人
     */
    public static final String JWT_DEFAULT_ISSUER = "JacksonTu";

    /**
     * 签发的目标
     */
    public static final String JWT_DEFAULT_AUDIENCE = "web";

    /**
     * JWT 默认过期时间，3600L，单位秒
     */
    public static final int JWT_DEFAULT_EXPIRE_SECOND = 3600;


    /**
     * 登录用户Token令牌缓存KEY前缀
     */
    public static final String JWT_PREFIX_USER_TOKEN = "hdw_user_token_";

    /**
     * 系统日志类型： 登录
     */
    public static final int LOG_TYPE_1 = 1;

    /**
     * 系统日志类型： 操作
     */
    public static final int LOG_TYPE_0 = 0;

    /**
     * 操作日志类型： 查询
     */
    public static final int OPERATE_TYPE_1 = 1;

    /**
     * 操作日志类型： 添加
     */
    public static final int OPERATE_TYPE_2 = 2;

    /**
     * 操作日志类型： 更新
     */
    public static final int OPERATE_TYPE_3 = 3;

    /**
     * 操作日志类型： 删除
     */
    public static final int OPERATE_TYPE_4 = 4;

    /**
     * 操作日志类型： 导入
     */
    public static final int OPERATE_TYPE_5 = 5;

    /**
     * 操作日志类型： 导出
     */
    public static final int OPERATE_TYPE_6 = 6;

}
