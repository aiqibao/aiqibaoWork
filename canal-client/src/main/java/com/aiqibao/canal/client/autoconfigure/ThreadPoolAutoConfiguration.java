package com.aiqibao.canal.client.autoconfigure;

import com.aiqibao.canal.client.handler.CanalThreadUncaughtExceptionHandler;
import com.aiqibao.canal.client.properties.CanalProperties;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @Author:aiqibao
 * @Date:2020/12/8 11:58
 * Best wish!
 */
@Configuration
@ConditionalOnProperty(value = CanalProperties.CANAL_ASYNC, havingValue = "true", matchIfMissing = true)
public class ThreadPoolAutoConfiguration {
    @Bean(destroyMethod = "shutdown")
    public ExecutorService executorService() {
        BasicThreadFactory factory = new BasicThreadFactory.Builder().namingPattern("canal-execute-thread-%d")
                .uncaughtExceptionHandler(new CanalThreadUncaughtExceptionHandler()).build();
        return Executors.newFixedThreadPool(10, factory);
    }
}
