package dynamic.jdk;

/**
 * @Author:aiqibao
 * @Date:2021/1/26 16:28
 * Best wish!
 */
public class Test {
    public static void main(String[] args) throws Exception {
        System.getProperties().put("sun.misc.ProxyGenerator.saveGeneratedFiles","true") ;
        Calculator c = (Calculator)new CalculatorProxy().getInstance(new MyCalculator()) ;
        int result = c.div(1,2) ;
        System.out.println(result);
    }
}
