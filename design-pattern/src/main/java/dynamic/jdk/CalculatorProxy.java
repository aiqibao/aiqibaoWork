package dynamic.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author:aiqibao
 * @Date:2021/1/26 16:28
 * Best wish!
 */
public class CalculatorProxy implements InvocationHandler {

    private Object target;

    public Object getInstance(Object target){
        this.target = target ;
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(),this) ;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        before();
        Object result = method.invoke(target,args) ;
        after() ;
        return result;
    }

    private void before(){
        System.out.println("Proxy before method");
    }

    private void after(){
        System.out.println("Proxy after method");
    }
}
