package com.aiqibao.canal.spring;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

/**
 * @author wzf
 * @version 1.0  2020/12/07
 */
@Service
public class SpringContextHolder implements ApplicationContextAware {
    private static ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
    }
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    public static Object getBean(String beanName) {
        return applicationContext.getBean(beanName);
    }

    public static <T>T getBean(String beanName , Class<T>clazz) {
        return (T)applicationContext.getBean(beanName , clazz);
    }
}
