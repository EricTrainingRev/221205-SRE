public abstract class Food {
    /*
     * An abstract class can not have objects created: you must instead have a sub-class inherit the properties
     * of the abstract class an create an object of that sub-class to access the properties of the abstract class
     */
    public String name;
    public String taste;
    public int calorieCount;
    public boolean isCandy;
    public boolean isCooked;
    public String texture;
    public String smell;

    

    public Food(String name, String taste, int calorieCount, boolean isCandy, boolean isCooked, String texture,
        String smell) {
        this.name = name;
        this.taste = taste;
        this.calorieCount = calorieCount;
        this.isCandy = isCandy;
        this.isCooked = isCooked;
        this.texture = texture;
        this.smell = smell;
    }

    public Food() {}



    public static void main(String[] args) {
        // Food myFood = new Food(); this is not legal code, you will get a compilation error if you try this
     }

    public abstract void eat();

    public abstract void cook();

    public abstract void store();
}
