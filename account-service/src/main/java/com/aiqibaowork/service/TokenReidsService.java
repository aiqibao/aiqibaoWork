package com.aiqibaowork.service;

/**
 * @Author:aiqibao
 * @Date:2020/10/29 15:29
 * Best wish!
 */
public interface TokenReidsService {
    /**
     * 往redis存入token
     * @param phoneNum  key
     * @param token     value
     * @param exphours
     */
    void put(String phoneNum,String token,Integer exphours);

    /**
     * 根据key获取token
     * @param phoneNum
     * @return
     */
    String get(String phoneNum);

    /**
     * 设置失效时间
     * @param phoneNum
     * @param exphours
     * @return
     */
    Boolean expire(String phoneNum,Integer exphours);

    /**
     * 删除key
     * @param phoneNum
     */
    void delete(String phoneNum) ;

}
