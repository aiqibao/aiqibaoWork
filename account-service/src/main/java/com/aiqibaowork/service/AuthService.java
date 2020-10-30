package com.aiqibaowork.service;

/**
 * @Author:aiqibao
 * @Date:2020/10/29 15:19
 * Best wish!
 */

/**
 * token服务
 */
public interface AuthService {
    /**
     * 生成验证码
     * @param phoneNum 手机号
     * @return
     */
    String createToken(String phoneNum) ;

    /**
     * 检查验证码
     * @param token token
     * @return String
     */
    String checkToken(String token);

    /**
     * 删除token
     * @param token
     */
    void deleteToken(String token);
}
