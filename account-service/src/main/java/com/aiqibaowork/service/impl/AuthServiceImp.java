package com.aiqibaowork.service.impl;

import com.aiqibaowork.service.AuthService;
import com.aiqibaowork.service.TokenReidsService;
import com.aiqibaowork.util.JwtUtil;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;


import java.util.Date;

/**
 * @Author:aiqibao
 * @Date:2020/10/29 15:23
 * Best wish!
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImp implements AuthService {

    private TokenReidsService tokenReidsService;

    /**
     * token有效期60天
     */
    private static final Integer EXP_HOURS = 24*60 ;
    /**
     * 生成token
     *
     * @param subject 手机号
     * @return
     */
    @Override
    public String createToken(String subject) {
        String jwtStr = JwtUtil.createJwt(subject,new Date()) ;
        tokenReidsService.put(subject,jwtStr,EXP_HOURS);
        return jwtStr;
    }

    /**
     * 检查验证码
     *
     * @param token token
     * @return String
     */
    @Override
    public String checkToken(String token) {
        try{
            Claims claims = JwtUtil.parseJwt(token) ;
            if (claims!=null){
                String tokenT = tokenReidsService.get(claims.getSubject()) ;
                log.info("缓存中的认证key为{},value为{}",claims.getSubject(),tokenT);
                if (StringUtils.isNotEmpty(tokenT) && token.equals(tokenT)){
                    log.info("验证Token是否有效-Token仍有效，延长token有效期phone：{},token:{}",claims.getSubject(),tokenT);
                    tokenReidsService.expire(claims.getSubject(),EXP_HOURS);
                    return claims.getSubject() ;
                }else{
                    log.info("验证Token是否有效-Token已失效-token已过期 - token：{}",token);
                    return "1" ;
                }
            }else{
                log.info("验证Token是否有效-Token已失效-token解析失败-token：{}",token);
                return "1" ;
            }
        } catch(Exception e){
            log.error("验证Token是否失效 - 服务器异常",e);
            return "1" ;
        }
    }

    /**
     * 删除token
     *
     * @param subject
     */
    @Override
    public void deleteToken(String subject) {
        tokenReidsService.delete(subject);
    }
}
