package com.hdw.system.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * @Description shiro属性配置
 * @Author TuMingLong
 * @Date 2019/11/1 14:27
 */
@Data
@ConfigurationProperties(prefix = "hdw.shiro")
public class ShiroProperties {

    /**
     * shiro 类型 jwt/cas
     */
    private String type;
    /**
     * shiro session名称前缀
     */
    private String sessionPrefix;
    /**
     * shiro cache名称前缀
     */
    private String cachePrefix;
    /**
     * shiro cookie名称前缀
     */
    private String cookiePrefix;

    /**
     * 登录用户Token令牌缓存KEY前缀
     */
    private String userTokenPrefix;

}
