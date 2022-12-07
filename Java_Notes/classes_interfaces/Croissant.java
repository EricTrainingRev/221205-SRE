package classes_interfaces;
public class Croissant extends Food {

    public Croissant(String name, String taste, int calorieCount, boolean isCandy, boolean isCooked, String texture,
            String smell) {
        super(name, taste, calorieCount, isCandy, isCooked, texture, smell);
    }

    public Croissant() {
    }

    @Override
    public void cook() {
        System.out.println("make the dough, bake it for just the right amount of time at just the right temp");
        this.isCooked = true;
        
    }

    @Override
    public void eat() {
        System.out.println("tenderly hold the bread and take a nibble");
        
    }

    @Override
    public void store() {
        System.out.println("what are you doing?! You want to eat these things fresh!");
        
    }

    
    
}
