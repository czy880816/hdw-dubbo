package com.hdw.system.shiro.jwt;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @Description com.hdw.base.shiro.jwt
 * @Author TuMingLong
 * @Date 2019/10/31 10:04
 */
public class JwtToken implements AuthenticationToken {

    private String token;

    public JwtToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
