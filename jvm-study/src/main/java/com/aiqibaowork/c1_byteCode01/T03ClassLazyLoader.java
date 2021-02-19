package com.aiqibaowork.c1_byteCode01;

/**
 * @Author:aiqibao
 * @Date:2021/2/5 16:04
 * Best wish!
 */
public class T03ClassLazyLoader {
    public static void main(String[] args) {
        System.out.println(S.b);
    }
    public static class S{
        final static int a = 1 ;
        static int b;
        static {
            b = 2 ;
            System.out.println("static");
        }
        public S(){
            b = 20 ;
        }
    }
}
