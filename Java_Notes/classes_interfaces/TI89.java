package classes_interfaces;
public class TI89 implements Maths {

    @Override
    public void add(float numOne, float numTwo) {
        System.out.println(numOne+numTwo);
        
    }

    @Override
    public void subtract(float numOne, float numTwo) {
        System.out.println(numOne-numTwo);
        
    }

    @Override
    public void multiply(float numOne, float numTwo) {
        System.out.println(numOne*numTwo);
        
    }

    @Override
    public void divide(float numOne, float numTwo) {
        System.out.println(numOne/numTwo);
        
    }
    
}
