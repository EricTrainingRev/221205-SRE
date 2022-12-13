# Week 2 QC review

## Java Primitives
Primitives are the smallest units of data in Java: they have no properties (fields or methods) and represent the lowest level of data you can work with in the language
```Java
boolean myBoolean = true; // could be false instead
char myCharacter = 'a'; // single character surrounded by single quotes
short myShort = 0;
byte myByte = 0;
int myInt = 0;
long myLong = 0;
float myFloat = 0.0f; // without the f at the end will default to double
double myDouble = 0.0;
```

## Variable Scope
Whether or not a variable/field can be accessed is determined by its "scope". Scope is determined by WHERE the value is defined.
```java
public class myClass{

    /*
     * fields defined in a class can either be class/static scope or instance/object scope.
     * See below for an example of each
     */

    static int classCount = 0; // class scope: available to all myClass objects
    String objectName; // instance scope: each object of this class will have their own objectName field

    public myClass(String objectName){
        /*
         * See how the addOnetoClass variable is defined in this constructor? That makes it part of the 
         * Method scope, so the variable can ONLY be referenced within this method
         */
        int addOnetoClass = 1; // method scope: only available within this method
        myClass.classCount += addOneToClass; // notice the variable in the static scope is referenced directly
        this.objectName = objectName; // notice the variable in the instance scope is referenced with the "this" keyword
    }

    public static void countClass(){
        if (myClass.classCount > 0){
            System.out.println(myClass.classCount);
        } else {
            /*
             * See how the zero variable is defined in this else block of code? That makes it part of the
             * block scope, so the variable can ONLY be referenced within this else block
             */
            int zero = 0; //block scope: only exists here in the code
            System.out.println(zero);
        }
    }
}
```
## Object Oriented Programming
There are four "pillars" of object oriented programming:
- Abstraction
    - The process of hiding implementation and processes of an entity to reduce complexity or increase understanding of a system's properties
        - Think Maven dependencies: we can implement third party code in our own applications without needing to understand how those libraries work or are organized.
- Polymorphism
    - The ability for entities to alter functionality while maintaining structure
        - think constructors in a class: you can have a no args constructor and a constructor that initializes all fields of the class, but both of them share the same name
- Inheritance
    - The ability for entities to adopt fields and behaviors from a parent (called the super) class, allowing for instantiation of child objects from said class
        - If you needed to create a Cat and Dog class, you could have them inherit all shared fields and behaviors from a parent Animal class
- Encapsulation
    - The act of wrapping code into a single unit and then selectively exposing and restricting access to that code based on functionality or use within the class
        - The Java Bean design pattern is the perfect example of Encapsulation: all fields are marked private, and any and all interactions with them are facilitated by public getter and setter methods

## Wrapper Classes
Java primitives are limited in what they can do: they have no associated fields or methods of their own. This can cause a multitude of problems, such as when you want to convert an int into a String. Java has Wrapper classes to get around this issue: Wrapper classes are the class version of a primitive. So, the int primitive has a Wrapper class called Integer that provides convinience methods like toString() that can convert the int into a String. 

Another benefit of Wrapper classes can be seen when using interfaces like List and Set, which requires classes when setting their generics (List\<String\>, List\<MyClass\>, etc). Without the Wrapper classes you would be unable to store the primitive values in any lists or sets. When you add the primitive to the collection Java performs "Autoboxing" to convert the primitive into its Object form for storage inside of the collection. When you retrieve the data, it performs "Unboxing" to convert the Object into its primitive form.
```Java
int myint = 1; // creates a primitive value
Integer myInteger = Integer.valueOf(1); // creates an object, normally you would not do this manually

List<Integer> myNumbers = new ArrayList<>();

myNumbers.add(1); // takes a primitive argument and Autoboxes it into an Integer object before storing it in the List

myNumbers.get(0) // Unboxes the Integer object into a primitive and returns it from the container
```

## Stack & Heap
When you run a Java application the objects and primitives you are working with need to be stored somewhere. The area where objects are stored is references as the Heap, variables and primitives are stored in a space known as the stack. A Stack is created anytime a method is called, and any primitive values created during the execution of that method live in that stack. So, take the following code:
```java
public static void main(String[] args){
    String greeting = "Hello world!";
    System.out.println(greeting);
    int x = 5;
    int y = 7;
    System.out.println(x + y);
}
```
When the main method is executed a stack is created for it. The first line of code for the main method creates a String object called greeting. The variable lives in the stack, the String object it represents is stored in the heap (specifically the String pool), which allows the object to continue existing outside of any specific stack. When the println method is called the greeting variable is moved to the new stack that is created on top of the main stack, and then the code associated with the println method is executed. Upon completion, the println stack is removed and the main method stack continues to execute. The variables X and Y have their values initialized, and then they are passed into the stack created by the second println method. After the execution of the second println method the stack that was created for it is removed and the main method stack continues to execute. Because there is no more code to execute the main method stack is removed from memory and the application ends.