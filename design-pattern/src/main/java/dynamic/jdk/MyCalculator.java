package dynamic.jdk;

/**
 * @Author:aiqibao
 * @Date:2021/1/26 16:24
 * Best wish!
 */
public class MyCalculator implements Calculator,Person {
    @Override
    public int add(int i, int j) {
        return i+j;
    }

    @Override
    public int sub(int i, int j) {
        return i-j;
    }

    @Override
    public int mult(int i, int j) {
        return i*j;
    }

    @Override
    public int div(int i, int j) throws Exception {
        if (j==0){
            throw new Exception("j is not equal to zero") ;
        }
        return i/j;
    }

    public void setName(){
        System.out.println("111111111");
    }

    @Override
    public void getName() {

    }
}
