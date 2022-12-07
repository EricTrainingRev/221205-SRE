package classes_interfaces;
public class Animal {

    public int limbs;
    public String name;
    public String size;
    public String eyeColor;

    public Animal(){}

    public Animal(int limbs, String name, String size, String eyeColor){
        this.limbs = limbs;
        this.name = name;
        this.size = size;
        this.eyeColor = eyeColor;
    }


    public void breath(){
        System.out.println("wheeze");
    }

    public void makeNoise(){
        System.out.println("noises");
    }
}
