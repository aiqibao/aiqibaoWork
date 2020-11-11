package com.aiqibaowork.eureka.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author:aiqibao
 * @Date:2020/11/6 17:03
 * Best wish!
 */
@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        /**
         * 默认情况下添加SpringSecurity依赖的应用每个请求都需要添加CSRF token才能访问，Eureka
         * 客户端注册时并不会添加，所以需要配置/eurake/**路径不需要CSRF token
         */
       http.csrf().ignoringAntMatchers("/eureka/**") ;
       http.authorizeRequests().anyRequest().authenticated().and().httpBasic();
    }
}
