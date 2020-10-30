package com.aiqibaowork.service.impl;

import com.aiqibaowork.service.TokenReidsService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * @Author:aiqibao
 * @Date:2020/10/29 15:33
 * Best wish!
 */
@Service
public class TokenRedisServiceImp implements TokenReidsService {
    /** 前缀 */
    private static final String PRE_KEY = "token:";
    @Resource(name="redisTemplate")
    private ValueOperations<String,String> vops ;
    @Resource(name="redisTemplate")
    private RedisTemplate<String,String> redisTemplate ;
    /**
     * 往redis存入token
     *
     * @param phoneNum key
     * @param token    value
     * @param exphours
     */
    @Override
    public void put(String phoneNum, String token, Integer exphours) {
        vops.set(PRE_KEY+phoneNum,token,exphours, TimeUnit.HOURS);
    }

    /**
     * 根据key获取token
     *
     * @param phoneNum
     * @return
     */
    @Override
    public String get(String phoneNum) {
        return vops.get(PRE_KEY+phoneNum);
    }

    /**
     * 设置失效时间
     *
     * @param phoneNum
     * @param exphours
     * @return
     */
    @Override
    public Boolean expire(String phoneNum, Integer exphours) {
        return redisTemplate.expire(PRE_KEY+phoneNum,exphours,TimeUnit.HOURS);
    }

    /**
     * 删除key
     *
     * @param phoneNum
     */
    @Override
    public void delete(String phoneNum) {
        redisTemplate.delete(PRE_KEY+phoneNum) ;
    }
}
