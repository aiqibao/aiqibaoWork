package com.aiqibaowork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @Author:aiqibao
 * @Date:2020/10/29 12:12
 * Best wish!
 */
@SpringBootApplication
@EnableAsync
@MapperScan("com.aiqibaowork.mapper")
public class AccountServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountServiceApplication.class,args) ;
    }
}
