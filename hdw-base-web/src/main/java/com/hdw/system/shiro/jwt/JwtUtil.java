package com.hdw.system.shiro.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.hdw.common.constants.CommonConstants;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.DateUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @Description JWT工具类
 * @Author TuMingLong
 * @Date 2019/10/31 10:29
 */
@Slf4j
public class JwtUtil {

    /**
     * 生成JWT Token
     *
     * @param username     用户名
     * @param secret       用户密码
     * @param expireSecond 过期时间
     * @return
     */
    public static String generatorToken(String username, String secret, Integer expireSecond) {
        if (StringUtils.isBlank(username)) {
            log.error("username不能为空");
            return null;
        }
        log.debug("username:{}", username);

        // 如果盐值为空，则使用默认值：666666
        if (StringUtils.isBlank(secret)) {
            secret = CommonConstants.JWT_DEFAULT_SECRET;
        }
        log.debug("secret:{}", secret);

        // 过期时间，单位：秒，默认过期时间为1小时
        if (expireSecond != null) {
            expireSecond = CommonConstants.JWT_DEFAULT_EXPIRE_SECOND;
        }
        log.debug("expireSecond:{}", expireSecond);

        Date expireDate = DateUtils.addSeconds(new Date(), expireSecond);

        Algorithm algorithm = Algorithm.HMAC256(secret);
        String token = JWT.create()
                .withClaim(CommonConstants.JWT_DEFAULT_USERNAME, username)
                .withIssuer(CommonConstants.JWT_DEFAULT_ISSUER) //签发人
                .withAudience(CommonConstants.JWT_DEFAULT_AUDIENCE) //签发的目标
                .withIssuedAt(new Date()) //签名时间
                .withExpiresAt(expireDate) //token过期时间
                .sign(algorithm); //签名
        return token;
    }


    /**
     * 校验token是否正确
     *
     * @param token  密钥
     * @param secret 用户的密码
     * @return 是否正确
     */
    public static boolean verifyToken(String token, String username, String secret) {
        try {
            // 根据密码生成JWT效验器
            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withClaim(CommonConstants.JWT_DEFAULT_USERNAME, username)
                    .withIssuer(CommonConstants.JWT_DEFAULT_ISSUER)
                    .withAudience(CommonConstants.JWT_DEFAULT_AUDIENCE)
                    .build();
            // 效验TOKEN
            DecodedJWT jwt = verifier.verify(token);
            if (jwt != null) {
                return true;
            }
        } catch (Exception e) {
            log.error("Verify Token Exception", e);
        }
        return false;
    }


    /**
     * 获得token中的信息无需secret解密也能获得
     *
     * @return token中包含的用户名
     */
    public static String getUsername(String token) {
        if (StringUtils.isBlank(token)) {
            return null;
        }
        try {
            DecodedJWT jwt = JWT.decode(token);
            if (jwt == null) {
                return null;
            }
            String username = jwt.getClaim(CommonConstants.JWT_DEFAULT_USERNAME).asString();
            return username;
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * 从请求头或者请求参数中获取用户账号
     *
     * @param request
     * @return
     */
    public static String getUserNameByToken(HttpServletRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("request不能为空");
        }
        // 从请求头中获取token
        String token = request.getHeader(CommonConstants.JWT_DEFAULT_TOKEN_NAME);
        if (StringUtils.isBlank(token)) {
            // 从请求参数中获取token
            token = request.getParameter(CommonConstants.JWT_DEFAULT_TOKEN_NAME);
        }
        String username = getUsername(token);
        if (StringUtils.isBlank(username)) {
            throw new RuntimeException("未获取到用户");
        }
        return username;
    }

}
