package basics;
import java.util.ArrayList;
import java.util.List;

// We have to import the List Interface and ArrayList class to our file with lines 1 and 2 because
// their information can not be found in the package (think folder with .java files) our current file is
// located in


public class MoreDataStructures {
    /*
     * Two interfaces you are going to want to become familiar with are the List and Set interfaces. These two
     * give you access to some of the more common data structures you will want to use in your Java code
     */

    /*
     * Lists are going to be your most flexible data structures: Lists are indexable, they retain order of insertion,
     * and they allow duplicate values. The most common kind of List you will use is an ArrayList. 
     * Lists make use of Generics: these are placeholders that tell Java what KIND of data is going to be used
     * with the object in question
     */

    
    public static void main(String[] args) {
       List<String> namesList = new ArrayList<>(); // ArrayList has diamond brackets as well, but they will stay empty
       namesList.add("Billy");
       namesList.add("Sally");
       namesList.add("Teddy");
       namesList.add(0,"Adam");
       /*
        * Above we have a great example of method overloading: it is the same add method being called in all
        * instances, but the way the method works changes depending on how many arguments we provide the method
        * if we don't provide an index position then our String gets added to the end of the list, but if we provide
        * an index position then we can determine what index position the String will be placed in, and all other Strings
        * in the list will be adjusted accordingly
        */
       System.out.println(namesList);

       
    }
    
}
