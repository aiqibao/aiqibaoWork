package dynamic.cglib;

import net.sf.cglib.core.DebuggingClassWriter;
import net.sf.cglib.proxy.Enhancer;

/**
 * @Author:aiqibao
 * @Date:2021/1/27 10:56
 * Best wish!
 */
public class Test {
    public static void main(String[] args) {
        System.setProperty(DebuggingClassWriter.DEBUG_LOCATION_PROPERTY,"G:\\workspace\\github\\aiqibaoWork\\design-pattern\\target\\classes");
        Enhancer enhancer = new Enhancer();
        enhancer.setSuperclass(MyCalculator.class);
        enhancer.setCallback(new MyCglib());
        MyCalculator o = (MyCalculator) enhancer.create();
        o.add(1,1) ;
        System.out.println(o.getClass());
    }
}
