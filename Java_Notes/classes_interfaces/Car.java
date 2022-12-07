package classes_interfaces;
public class Car {
    // for this example we can pretend we are writing an app for a car videogame

    // All cars are going to share some properties in our code
    public String owner;
    public int gas;
    public static int count; // anytime you see the static keyword in a class know that the field/method is SHARED across all instances of the class

    // All classes have a special method (methods are repeatable code) that handles creating an object
    // of the class. This is called instantiation.

    // what we are doing here is creating our own constructor that handles setting the value of the owner
    // field when any car object is created
    public Car(String startingOwner, int startingGas){
        // "this" is a reference to the object being created
        // we are setting whatever the value of startingOwner is to the object's owner field
        this.owner = startingOwner;
        this.gas = startingGas;
        count++; // notice I don't need the "this" keyword, since the field is shared across all objects of the Car class
    }

    // you can create more than one constructor, the compiler will know how to handle them in your code depending
    // on the arguments you provide.
    public Car(){
        count++; // notice I don't need the "this" keyword, since the field is shared across all objects of the Car class
    }

    public static void main(String[] args) {
        System.out.println(Car.count); // this should say 0 because no cars have been made at this point

        Car georgeClooneyCar = new Car(); // here we call the no args constructor to make a Car object with no fields set
        System.out.println(Car.count); // this should now say 1 because there is one car object created in our code

        georgeClooneyCar.owner = "George Clooney"; // here we set the value of the myCar Car object field owner to "george clooney"

        System.out.println(georgeClooneyCar.owner); // here we confirm that we set the value of the owner field in the myCar object

        Car mattDamonCar = new Car("Matt Damon", 100); // here we call the constructor that initializes the owner field of the object
        System.out.println(Car.count); // this should now say 2 because there are two car objects created in our code

        System.out.println(mattDamonCar.gas); // we can see the results without having to manually set the value in a second line of code
        System.out.println(georgeClooneyCar.gas);
        // to call the drive method I MUST have a Car object to do it: I can't call the method by itself
        mattDamonCar.drive(); // make sure not to forget the () when you call a method

        System.out.println(mattDamonCar.gas); // because we made the drive method reduce the gas of the car the gas field is now lower
        System.out.println(georgeClooneyCar.gas);

        checkCount();

    }

    /*
     * public: access modifier, in this case, we are saying this method can be called by any object anywhere in our code
     * void: return type, in this case, we are saying this method does not return any data
     * drive: name of our method, method names should give a decent idea of what is being accomplished by your code
     */
    public void drive(){
        System.out.println("vroom vroom!");
        this.gas-=10;
    }

    // by adding the static keyword to the method we indicate the method belongs to the class proper, and so is called by the class, not individual objects
    public static void checkCount(){
        System.out.println("there are " + count + " cars created" );
    }

}
