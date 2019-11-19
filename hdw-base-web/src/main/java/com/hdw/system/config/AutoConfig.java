package com.hdw.system.config;

import com.hdw.system.interceptor.DownloadInterceptor;
import com.hdw.system.interceptor.ResourceInterceptor;
import com.hdw.system.interceptor.UploadInterceptor;
import com.hdw.system.properties.HdwCommonProperties;
import com.hdw.system.properties.InterceptorProperties;
import com.hdw.system.properties.ShiroProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description 配置
 * @Author TuMingLong
 * @Date 2019/11/1 14:58
 */
@Slf4j
@Configuration
@EnableConfigurationProperties({
        HdwCommonProperties.class,
        InterceptorProperties.class,
        ShiroProperties.class
})
public class AutoConfig {

    /**
     * 资源拦截器
     *
     * @return
     */
    @Bean
    public ResourceInterceptor resourceInterceptor() {
        ResourceInterceptor resourceInterceptor = new ResourceInterceptor();
        return resourceInterceptor;
    }

    /**
     * 上传拦截器
     *
     * @return
     */
    @Bean
    public UploadInterceptor uploadInterceptor() {
        UploadInterceptor uploadInterceptor = new UploadInterceptor();
        return uploadInterceptor;
    }

    /**
     * 下载拦截器
     *
     * @return
     */
    @Bean
    public DownloadInterceptor downloadInterceptor() {
        DownloadInterceptor downloadInterceptor = new DownloadInterceptor();
        return downloadInterceptor;
    }
}
