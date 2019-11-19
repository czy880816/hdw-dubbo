package com.hdw;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * @author TuMinglong
 * @description Application
 * @date 2017年9月5日下午8:55:08
 */
@SpringBootApplication
public class BaseServiceApplication extends SpringBootServletInitializer {

    protected final static Logger logger = LoggerFactory.getLogger(BaseServiceApplication.class);

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        logger.info("----BaseServiceApplication 启动----");
        return application.sources(BaseServiceApplication.class);

    }

    public static void main(String[] args) {
        SpringApplication.run(BaseServiceApplication.class, args);
        logger.info("----BaseServiceApplication 启动----");
    }

}
