package com.hdw.system.aspect;

import com.hdw.common.base.entity.LoginUser;
import com.hdw.common.constants.CommonConstants;
import com.hdw.common.utils.JacksonUtils;
import com.hdw.common.utils.SpringContextUtils;
import com.hdw.system.entity.SysLog;
import com.hdw.system.service.ISysLogService;
import com.hdw.system.shiro.ShiroKit;
import org.apache.dubbo.config.annotation.Reference;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.util.Date;

/**
 * @Description com.hdw.system.aspect
 * @Author TuMingLong
 * @Date 2019/11/13 14:58
 */
@Aspect
@Component
public class SysLogAspect {

    private static final Logger logger = LoggerFactory.getLogger(SysLogAspect.class);

    @Reference
    private ISysLogService sysLogService;


    //@Pointcut("within(@org.springframework.stereotype.Controller *)")
    @Pointcut("within(@org.springframework.web.bind.annotation.RestController *)")
    public void logPointCut() {

    }

    @Around("logPointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        long beginTime = System.currentTimeMillis();
        //执行方法
        Object result = point.proceed();
        //执行时长(毫秒)
        long time = System.currentTimeMillis() - beginTime;

        //保存日志
        saveSysLog(point, time);

        return result;
    }

    @AfterReturning(returning = "object", pointcut = "logPointCut()")
    public void doAfterReturning(Object object) {
        if (object != null) {
            logger.info("response={}", JacksonUtils.toJson(object));
        } else {
            logger.info("response=");
        }

    }

    private void saveSysLog(ProceedingJoinPoint joinPoint, long time) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();

        SysLog sysLog = new SysLog();
        //日志类型
        sysLog.setLogType(0);
        //请求的方法名
        String className = joinPoint.getTarget().getClass().getName();
        String methodName = signature.getName();
        sysLog.setMethod(methodName);
        sysLog.setClassName(className);

        //设置操作类型
        if (sysLog.getLogType() == CommonConstants.LOG_TYPE_0) {
            sysLog.setOperateType(getOperateType(methodName));
        }

        //请求的参数
        Object[] args = joinPoint.getArgs();
        try{
            String params = JacksonUtils.toJson(args);
            sysLog.setParams(params);
        }catch (Exception e){
           e.printStackTrace();
        }

        //获取request
        HttpServletRequest request = SpringContextUtils.getHttpServletRequest();
        //设置IP地址
        sysLog.setClientIp(request.getRemoteAddr());

        //获取登录用户信息
        LoginUser sysUser = ShiroKit.getUser();
        if(sysUser!=null){
            sysLog.setLoginName(sysUser.getLoginName());
        }
        //耗时
        sysLog.setTime(time);
        sysLog.setCreateTime(new Date());
        //保存系统日志
        sysLogService.save(sysLog);

        //ip
        logger.info("ip={}", request.getRemoteAddr());
        //URL
        logger.info("url={}", request.getRequestURL());
        //method
        logger.info("className={}", className);
        //类方法
        logger.info("methodName={}", methodName);
        //参数
        logger.info("args={}", sysLog.getParams());
        //执行时长
        logger.info("time={}", time+"ms");
    }
    /**
     * 获取操作类型
     */
    private int getOperateType(String methodName) {
        if (methodName.startsWith("list")) {
            return CommonConstants.OPERATE_TYPE_1;
        }
        if (methodName.startsWith("save")) {
            return CommonConstants.OPERATE_TYPE_2;
        }
        if (methodName.startsWith("update")) {
            return CommonConstants.OPERATE_TYPE_3;
        }
        if (methodName.startsWith("delete")) {
            return CommonConstants.OPERATE_TYPE_4;
        }
        if (methodName.startsWith("import")) {
            return CommonConstants.OPERATE_TYPE_5;
        }
        if (methodName.startsWith("export")) {
            return CommonConstants.OPERATE_TYPE_6;
        }
        return CommonConstants.OPERATE_TYPE_1;
    }
}
