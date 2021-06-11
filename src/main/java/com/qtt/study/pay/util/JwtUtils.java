package com.qtt.study.pay.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * @author Ellie
 * @date 2021-06-11 下午3:13
 */
public class JwtUtils {
    public static final long EXPIRE = 1000 * 60 * 60 * 24;
    public static final String APP_SECRET = "ellie test"; //秘钥

    /**
     * generate token by id and name
     *
     * @param id
     * @param name
     * @return
     */
    public static String getJwtToken(String id, String name) {
        String jwtToken = Jwts.builder().setHeaderParam("typ", "JWT").setHeaderParam("alg", "HS256")//这两个值固定
                .setSubject("user").setIssuedAt(new Date()).setExpiration(new Date(System.currentTimeMillis() + EXPIRE))
                .claim("mobile", id).claim("password", name).signWith(SignatureAlgorithm.HS256, APP_SECRET).compact();
        return jwtToken;
    }

    /**
     * check token
     *
     * @param jwtToken
     * @return
     */
    public static boolean checkToken(String jwtToken) {
        if (StringUtils.isEmpty(jwtToken)) {
            return false;
        }
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(jwtToken);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    /**
     * check token
     * @param request
     * @return
     */
    public static boolean checkToken(HttpServletRequest request) {
        String token = request.getHeader("token");
        return checkToken(token);
    }

    /**
     * get info by token
     *
     * @param request
     * @return
     */
    public static String getInfoByJwtToken(HttpServletRequest request, String condition) {
        String token = request.getHeader("token");
        if (StringUtils.isEmpty(token)) {
            return "";
        }
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
        Claims claims = claimsJws.getBody();
        return (String) claims.get(condition);
    }
}
