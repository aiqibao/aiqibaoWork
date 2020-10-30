package com.aiqibaowork.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

/**
 * @Author:aiqibao
 * @Date:2020/10/29 15:42
 * Best wish!
 */
public class JwtUtil {
    private static String secret = "ko346134h_we]rg3in_yip1!" ;

    /**
     * 生成token
     * @param subject
     * @param issueDate
     * @return
     */
    public static String createJwt(String subject,Date issueDate){
        String compactJwt = Jwts.builder()
                .setSubject(subject)
                .setIssuedAt(issueDate)
                .signWith(SignatureAlgorithm.HS512,secret)
                .compact();
        return compactJwt ;
    }

    public static Claims parseJwt(String jwt){
        Claims claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody();
        return claims ;
    }

}
