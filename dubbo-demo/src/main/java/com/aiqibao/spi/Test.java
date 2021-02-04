package com.aiqibao.spi;

import org.apache.dubbo.common.extension.ExtensionLoader;

import java.util.ServiceLoader;

/**
 * @Author:aiqibao
 * @Date:2021/2/3 10:01
 * Best wish!
 */
public class Test {

    public static void main(String[] args) {
        System.out.println("JAVA SPI");
        ServiceLoader<Person> load = ServiceLoader.load(Person.class);
        load.forEach(Person::getName);

        System.out.println("DUBBO SPI");
        ExtensionLoader<Person> extensionLoader = ExtensionLoader.getExtensionLoader(Person.class);
        //Person defaultExtension = extensionLoader.getDefaultExtension();
        //defaultExtension.getName();
        Person man = extensionLoader.getExtension("true");
        man.getName();

    }
}
