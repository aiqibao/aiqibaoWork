package com.aiqibao.spi;

/**
 * @Author:aiqibao
 * @Date:2021/2/3 9:39
 * Best wish!
 */
public class Woman implements Person {
    public static final String NAME = "woman" ;
    @Override
    public String getName() {
        System.out.println("i am woman");
        return "woman";
    }
}
