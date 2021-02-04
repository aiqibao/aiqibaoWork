package com.aiqibao.spi;

/**
 * @Author:aiqibao
 * @Date:2021/2/3 9:38
 * Best wish!
 */
public class Man implements Person {
    public static final String NAME = "man" ;
    @Override
    public String getName() {
        System.out.println("i am Man ");
        return "Man";
    }
}
