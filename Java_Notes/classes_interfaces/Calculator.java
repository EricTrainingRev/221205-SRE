package classes_interfaces;
public class Calculator implements Maths {

    public int[] convertFloat(float numOne, float numTwo){
        int[] nums = new int[2];
        nums[0] = Math.round(numOne);
        nums[1] = Math.round(numTwo);
        return nums;
    }

    @Override
    public void add(float numOne, float numTwo) {
        int[] integers = this.convertFloat(numOne, numTwo);
        System.out.println(integers[0]+integers[1]); // we print the value of our rounded numbers to the console
        
    }

    @Override
    public void subtract(float numOne, float numTwo) {
        // to turn these floats into ints we could do type casting: this will truncate any decimal values
        int intOne = (int)numOne;
        int intTwo = (int)numTwo;
        System.out.println(intOne-intTwo); // we print the value of our rounded numbers to the console
        
    }

    @Override
    public void multiply(float numOne, float numTwo) {
        int intOne = Math.round(numOne); // this will convert our float to an int by rounding it
        int intTwo = Math.round(numTwo);
        System.out.println(intOne*intTwo); // we print the value of our rounded numbers to the console
        
    }

    @Override
    public void divide(float numOne, float numTwo) {
        int intOne = Math.round(numOne); // this will convert our float to an int by rounding it
        int intTwo = Math.round(numTwo);
        System.out.println(intOne/intTwo); // we print the value of our rounded numbers to the console
        
    }
    
}
