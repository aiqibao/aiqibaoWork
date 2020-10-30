package com.aiqibaowork.cache;

import com.aiqibaowork.util.ServerLog;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

public class RedisCache extends ServerLog {
    @Autowired
    private StringRedisTemplate stringRedisTemplate ;

    private boolean cacheOn = true ;

    /**
     * 锁超时时间
     */
    private static final int LOCK_TIMEOUT = 10 ;

    /**
     * 最大次数
     */
    private static final int MAX_TIME = 10 ;

    /**
     * 尝试间隔（ms）
     */
    private static long TRY_INTERVAL = 300 ;

    /**
     * 锁超时时间单位
     */
    public static final TimeUnit LOCK_TIMEUNIT = TimeUnit.SECONDS ;

    public static final String CACHE_TRIGGER_KEY = "com.aiqibao.cache.on" ;

    public RedisCache(){
        this.cacheOn = new Boolean(System.getProperty(CACHE_TRIGGER_KEY,"true")) ;
    }

    /**
     * 尝试获取锁  成功时返回true，未获取到返回false，立即返回
     * @param key
     * @param value
     * @return
     */
    protected Boolean tryLock(String key,String value){
        return this.stringRedisTemplate.opsForValue().setIfAbsent(key,value,LOCK_TIMEOUT,LOCK_TIMEUNIT) ;
    }

    /**
     * 获取锁，会重复尝试一定次数
     */
        protected Boolean lock(String key,String value) throws InterruptedException {
            for (int i = 0;i<MAX_TIME;i++){
                boolean acquired = tryLock(key, value) ;
                if (acquired){
                    return true;
                }
                Thread.sleep(TRY_INTERVAL);
            }
            return false ;
    }

    /**
     * 主动释放锁
     */
    protected Boolean release(String key,String value){
        return null ;
    }

}
