package com.hdw.system.config;


import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;
import com.google.common.base.Charsets;
import com.hdw.common.constants.DatePattern;
import com.hdw.common.xss.XssStringJsonSerializer;
import com.hdw.system.config.json.jackson.deserializer.JacksonDateDeserializer;
import com.hdw.system.config.json.jackson.deserializer.JacksonDoubleDeserializer;
import com.hdw.system.config.json.jackson.serializer.JacksonDateSerializer;
import com.hdw.system.config.json.jackson.serializer.JacksonIntegerDeserializer;
import com.hdw.system.interceptor.DownloadInterceptor;
import com.hdw.system.interceptor.ResourceInterceptor;
import com.hdw.system.interceptor.UploadInterceptor;
import com.hdw.common.xss.XssStringJsonDeserializer;
import com.hdw.system.properties.HdwCommonProperties;
import com.hdw.system.properties.InterceptorProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import javax.annotation.PostConstruct;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * @Description WebMvc配置
 * @Author TuMingLong
 * @Date 2019/11/1 14:58
 */
@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private HdwCommonProperties commonProperties;

    @Autowired
    private UploadInterceptor uploadInterceptor;

    @Autowired
    private DownloadInterceptor downloadInterceptor;

    @Autowired
    private ResourceInterceptor resourceInterceptor;

    @PostConstruct
    public void init() {
        // 打印HdwCommonProperties配置信息
        log.debug("HdwCommonProperties：{}", JSON.toJSONString(commonProperties));
    }

    /**
     * 注册自定义拦截器，添加拦截路径和排除拦截路径
     * 添加文件上传类型拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        InterceptorProperties interceptorConfig = commonProperties.getInterceptor();

        // 上传拦截器
        if (interceptorConfig.getUpload().isEnabled()) {
            registry.addInterceptor(uploadInterceptor)
                    .addPathPatterns(interceptorConfig.getUpload().getIncludePaths());
        }

        // 资源拦截器注册
        if (interceptorConfig.getResource().isEnabled()) {
            registry.addInterceptor(resourceInterceptor)
                    .addPathPatterns(interceptorConfig.getResource().getIncludePaths());
        }

        // 下载拦截器注册
        if (interceptorConfig.getDownload().isEnabled()) {
            registry.addInterceptor(downloadInterceptor)
                    .addPathPatterns(interceptorConfig.getDownload().getIncludePaths());
        }
    }

    /**
     * 资源处理器
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("swagger-ui.html", "doc.html")
                .addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**")
                .addResourceLocations("classpath:/META-INF/resources/webjars/");
    }

    /**
     * 消息转换器
     *
     * @param converters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        MappingJackson2HttpMessageConverter jackson2HttpMessageConverter = new MappingJackson2HttpMessageConverter();

        ObjectMapper objectMapper = jackson2HttpMessageConverter.getObjectMapper();
        objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
        objectMapper.disable(DeserializationFeature.ADJUST_DATES_TO_CONTEXT_TIME_ZONE);

        SimpleModule simpleModule = new SimpleModule();
        // Long类型序列化成字符串，避免Long精度丢失
        simpleModule.addSerializer(Long.class, ToStringSerializer.instance);
        simpleModule.addSerializer(Long.TYPE, ToStringSerializer.instance);

        // XSS序列化
        simpleModule.addSerializer(String.class, new XssStringJsonSerializer());
        simpleModule.addDeserializer(String.class, new XssStringJsonDeserializer());

        // Date
        simpleModule.addSerializer(Date.class, new JacksonDateSerializer());
        simpleModule.addDeserializer(Date.class, new JacksonDateDeserializer());

        simpleModule.addDeserializer(Integer.class, new JacksonIntegerDeserializer());
        simpleModule.addDeserializer(Double.class, new JacksonDoubleDeserializer());

        // jdk8日期序列化和反序列化设置
        JavaTimeModule javaTimeModule = new JavaTimeModule();
        javaTimeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.yyyy_MM_dd_HH_mm_ss)));
        javaTimeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.yyyy_MM_dd_HH_mm_ss)));

        javaTimeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern(DatePattern.yyyy_MM_dd)));
        javaTimeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern(DatePattern.yyyy_MM_dd)));

        javaTimeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern(DatePattern.HH_mm_ss)));
        javaTimeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DatePattern.HH_mm_ss)));

        objectMapper.registerModule(simpleModule).registerModule(javaTimeModule).registerModule(new ParameterNamesModule());

        jackson2HttpMessageConverter.setObjectMapper(objectMapper);

        //放到第一个
        converters.add(0, jackson2HttpMessageConverter);
    }
}
