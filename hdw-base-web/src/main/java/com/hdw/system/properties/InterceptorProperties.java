package com.hdw.system.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 拦截器属性
 * @Author TuMingLong
 * @Date 2019/11/1 14:18
 */
@Data
@ConfigurationProperties(prefix = "hdw.interceptor")
public class InterceptorProperties {

    /**
     * 资源拦截器
     */
    @NestedConfigurationProperty
    private InterceptorConfig resource = new InterceptorConfig();

    /**
     * 上传拦截器
     */
    @NestedConfigurationProperty
    private InterceptorConfig upload = new InterceptorConfig();

    /**
     * 下载拦截器
     */
    @NestedConfigurationProperty
    private InterceptorConfig download = new InterceptorConfig();


    @Data
    public static class InterceptorConfig {

        /**
         * 是否启用
         */
        private boolean enabled;

        /**
         * 排除路径
         */
        private String[] excludePaths;

        /**
         * 包含的路径
         */
        private String[] includePaths;

    }
}
