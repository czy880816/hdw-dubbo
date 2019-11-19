package com.hdw.system.shiro.jwt;

import com.hdw.common.constants.CommonConstants;
import com.hdw.common.constants.ErrorCode;
import com.hdw.common.result.CommonResult;
import com.hdw.common.utils.JacksonUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.web.filter.authc.AuthenticatingFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @Description Shiro JWT授权过滤器
 * @Author TuMingLong
 * @Date 2019/11/1 9:48
 */
@Slf4j
public class JwtFilter extends AuthenticatingFilter {
    @Override
    protected AuthenticationToken createToken(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        //获取请求token
        String token = getRequestToken((HttpServletRequest) servletRequest);

        if (StringUtils.isBlank(token)) {
            return null;
        }

        return new JwtToken(token);
    }

    /**
     * 访问失败处理
     *
     * @param servletRequest
     * @param servletResponse
     * @return
     * @throws Exception
     */
    @Override
    protected boolean onAccessDenied(ServletRequest servletRequest, ServletResponse servletResponse) throws Exception {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        //对跨域提供支持
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-control-Allow-Origin", httpRequest.getHeader("Origin"));
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpResponse.setHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));
        // 返回401
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);

        // 设置响应码为401或者直接输出消息
        String url = httpRequest.getRequestURI();
        log.error("onAccessDenied url：{}", url);
        String json = JacksonUtils.toJson(CommonResult.fail().code(ErrorCode.UNAUTHORIZED.getCode()).msg(ErrorCode.UNAUTHORIZED.getMsg()));

        PrintWriter printWriter = httpResponse.getWriter();
        printWriter.write(json);
        printWriter.flush();
        printWriter.close();
        return false;
    }

    /**
     * 判断是否允许访问
     *
     * @param request
     * @param response
     * @param mappedValue
     * @return
     */
    @Override
    protected boolean isAccessAllowed(ServletRequest request, ServletResponse response, Object mappedValue) {
        String url = WebUtils.toHttp(request).getRequestURI();
        log.info("isAccessAllowed url:{}", url);
        if (this.isLoginRequest(request, response)) {
            return true;
        }
        boolean allowed = false;
        try {
            allowed = executeLogin(request, response);
        } catch (IllegalStateException e) { //not found any token
            log.error("Token不能为空 {}", e.getMessage());
        } catch (Exception e) {
            log.error("访问错误 {}", e.getMessage());
        }
        return allowed || super.isPermissive(mappedValue);
    }

    /**
     * 登录失败处理
     *
     * @param token
     * @param e
     * @param servletRequest
     * @param servletResponse
     * @return
     */
    @Override
    protected boolean onLoginFailure(AuthenticationToken token, AuthenticationException e, ServletRequest servletRequest, ServletResponse servletResponse) {
        log.error("onLoginFailure，token:" + token + ",error:" + e.getMessage(), e);
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        //对跨域提供支持
        httpResponse.setContentType("application/json;charset=utf-8");
        httpResponse.setHeader("Access-control-Allow-Origin", httpRequest.getHeader("Origin"));
        httpResponse.setHeader("Access-Control-Allow-Methods", "GET,POST,OPTIONS,PUT,DELETE");
        httpResponse.setHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));
        // 返回401
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        try {
            //处理登录失败的异常
            String json = JacksonUtils.toJson(CommonResult.fail().code(ErrorCode.UNAUTHORIZED.getCode()).msg(ErrorCode.UNAUTHORIZED.getMsg()));
            PrintWriter printWriter = httpResponse.getWriter();
            printWriter.write(json);
            printWriter.flush();
            printWriter.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    @Override
    protected boolean executeLogin(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String token = getRequestToken(httpServletRequest);

        JwtToken jwtToken = new JwtToken(token);
        // 提交给realm进行登入，如果错误他会抛出异常并被捕获
        getSubject(request, response).login(jwtToken);
        // 如果没有抛出异常则代表登入成功，返回true
        return true;
    }

    /**
     * 从请求头或者请求参数中获取
     */
    private String getRequestToken(HttpServletRequest httpRequest) {
        //从header中获取token
        String token = httpRequest.getHeader(CommonConstants.JWT_DEFAULT_TOKEN_NAME);
        //如果header中不存在token，则从参数中获取token
        if (StringUtils.isBlank(token)) {
            token = httpRequest.getParameter(CommonConstants.JWT_DEFAULT_TOKEN_NAME);
        }
        return token;
    }
}
