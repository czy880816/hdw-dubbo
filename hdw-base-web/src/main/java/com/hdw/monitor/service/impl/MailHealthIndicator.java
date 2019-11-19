package com.hdw.monitor.service.impl;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.stereotype.Component;

/**
 * @Description 自定义邮件检测
 * @Author TuMingLong
 * @Date 2019/11/13 15:46
 */
@Component
public class MailHealthIndicator implements HealthIndicator {


    @Override public Health health() {
        int errorCode = check();
        if (errorCode != 0) {
            return Health.down().withDetail("Error Code", errorCode) .build();
        }
        return Health.up().build();
    }
    int check(){
        //可以实现自定义的数据库检测逻辑
        return 0;
    }
}
