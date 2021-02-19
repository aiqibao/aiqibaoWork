package com.aiqibaowork.c1_byteCode01;

/**
 * @Author:aiqibao
 * @Date:2021/2/5 15:04
 * Best wish!
 */
public class ClassLoaderScope {
    public static void main(String[] args) {
        System.out.println(ClassLoaderScope.class.getClassLoader());
        String pathBoot = System.getProperty("sun.boot.class.path");
        System.out.println("--------------boot---------");
        System.out.println(pathBoot.replaceAll(";",System.lineSeparator()));
        String pathExt  = System.getProperty("java.ext.dirs") ;
        System.out.println("--------------ext---------");
        System.out.println(pathBoot.replaceAll(";",System.lineSeparator()));
        String pathApp = System.getProperty("java.class.path");
        System.out.println("--------------app---------");
        System.out.println(pathApp.replaceAll(";",System.lineSeparator()));
    }
}
