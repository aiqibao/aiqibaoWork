package com.aiqibaowork.c1_byteCode01;

/**
 * @Author:aiqibao@Date:2021/2/5 15:15
 *  * Best wish!
 *  */
public class T02ClassLoader {
    public static void main(String[] args) throws ClassNotFoundException {
        Class<?> aClass = T02ClassLoader.class.getClassLoader().loadClass("com.aiqibaowork.c0_basic.T");
        System.out.println(aClass.getName());
    }
}
    