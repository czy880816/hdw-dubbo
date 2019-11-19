package com.hdw.system.properties;

import com.hdw.common.config.swagger2.HdwSwaggerProperties;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.NestedConfigurationProperty;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * @Description 自定义属性配置
 * @Author TuMingLong
 * @Date 2019/11/1 14:24
 */
@Data
@ConfigurationProperties(prefix = "hdw")
public class HdwCommonProperties {
    /**
     * 过期时间
     */
    private int expire;

    /**
     * 拦截器配置
     */
    @NestedConfigurationProperty
    private InterceptorProperties interceptor;

//    /**
//     * 过滤器配置
//     */
//    @NestedConfigurationProperty
//    private FilterProperties filter;

    /**
     * 上传目录前缀
     */
    private String fileUploadPrefix;
    /**
     * 资源访问路径，前端访问
     */
    private String resourceAccessPath;
    /**
     * 资源访问路径，后段配置，资源映射/拦截器使用
     */
    private String resourceAccessPatterns;
    /**
     * 资源访问全路径
     */
    private String resourceAccessUrl;

    /**
     * 允许上传的文件后缀集合
     */
    private List<String> allowUploadFileExtensions;
    /**
     * 允许下载的文件后缀集合
     */
    private List<String> allowDownloadFileExtensions;

    /**
     * Shiro配置
     */
    @NestedConfigurationProperty
    private ShiroProperties shiro = new ShiroProperties();

    /**
     * 定时任务线程名称前缀
     */
    private String schedulerName;

    /**
     * 分布式锁模式
     * single 单机
     */
    private String redissonModel;

    /**
     * swagger配置
     */
    private HdwSwaggerProperties swagger2 = new HdwSwaggerProperties();

}
