package com.aiqibao.spi;

import org.junit.Test;

import java.util.ServiceLoader;

/**
 * @Author:aiqibao
 * @Date:2021/2/3 9:45
 * Best wish!
 */
public class SpiTest {
    @Test
    public void javaSpiTest(){
        System.out.println("11111");
        ServiceLoader<Person> load = ServiceLoader.load(Person.class);
        load.forEach(Person::getName);
    }
}
