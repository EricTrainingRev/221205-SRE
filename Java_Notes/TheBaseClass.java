public class TheBaseClass {

    public String name;
    public int age;
    public boolean isAwake;


    /*
     * In Java, ALL classes inherit from the base Object class. This is how we get access to things like constructors
     * or helpful methods like the toString() method.
     */
    

    public static void main(String[] args) {
        TheBaseClass obj = new TheBaseClass("Timmy", 47, false);
        System.out.println(obj);
    }


    public TheBaseClass(String name, int age, boolean isAwake) {
        this.name = name;
        this.age = age;
        this.isAwake = isAwake;
    }


    // this toString method is inherited from the base Java Object class, there are some others that are inherited as well
    @Override
    public String toString() {
        return "TheBaseClass [name=" + name + ", age=" + age + ", isAwake=" + isAwake + "]";
    }

    

    
    
    
    
}
