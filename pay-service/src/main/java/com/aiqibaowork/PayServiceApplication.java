package com.aiqibaowork;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @Author:aiqibao
 * @Date:2020/11/5 10:45
 * Best wish!
 */
@SpringBootApplication
@MapperScan("com.aiqibaowork.mapper")
public class PayServiceApplication {
    public static void main(String[] args){
        System.setProperty("spring.devtools.restart.enabled","false") ;
        SpringApplication.run(PayServiceApplication.class,args) ;
    }
}
