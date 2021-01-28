package com.aiqibao.rpc;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author:aiqibao
 * @Date:2021/1/28 15:33
 * Best wish!
 */

/*
    rpc：像本地方法一样调用远程的方法
 */
public class MyRPCTest {
    public void get(){
        Car car = proxyGet(Car.class);
    }

    private <T>T proxyGet(Class<T> tClass) {
        ClassLoader classLoader = tClass.getClassLoader();
        Class<?>[] interfaces = {tClass} ;
        Proxy.newProxyInstance(classLoader, interfaces, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String name = tClass.getName() ;
                String methodName = method.getName() ;
                Class<?>[] argsType = method.getParameterTypes() ;
                MyContent myContent = new MyContent() ;
                return null;
            }
        });
        return null;
    }
}
class MyContent{
    MyHead myHead = new MyHead() ;

}
class MyHead{
    int flag ;
    long length ;

}
interface Car{
    void getCarName();
}
interface Fly{
    void getFlyName();
}
