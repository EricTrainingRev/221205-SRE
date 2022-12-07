package basics;
public class Arrays {
    /*
     * Arrays are data structures in Java that we can use to hold other data. Arrays are immutable structures
     * that are set once they are created.
     */

    public static void main(String[] args) {
        int[] numbers = {1,3,5,7,9};

        int[] evenNumbers = new int[4]; // this array starts with four 0s inside of it
        int num = 2; // this variable represents the numbers we want to add into the array

        for(int x = 0; x < evenNumbers.length; x++){
            evenNumbers[x]=num; // set the value of the index position to num
            num+=2; // make sure that num turns into the new value we will need next iteration of our loop
        }

        for(int x = 0; x<evenNumbers.length; x++){
            System.out.println(evenNumbers[x]); // confirm we added the correct numbers to the array
        }

        /*
         * arrays will work a little differently if you are not working with primitives: let's see what happens
         * when we make an array of strings instead of numbers
         */

         String[] words = {"apple","Hello world!","Go Portland Trailblazers","Revature","coffee"};

         for(int x = 0; x < words.length; x++){
            System.out.println(words[x]);
         }

        String[] noWordsYet = new String[3];
        String characters = "abcd";

        for(int x = 0; x < noWordsYet.length; x++){
            noWordsYet[x] = characters;
            /*
             * Two things are happening below:
             * 1. the value of characters is being concatenated with itself, so "abcd" is being put with "abcd" 
             * to create a new string that looks like so: "abcdabcd"
             * 2. the variable characters is having its reference changed to the newly created string from step one.
             * It is important to realize that the initially created string did not change: it was used to create the new
             * string value and then it lost its previous reference (characters the variable).
             */
            characters = characters + characters; // this replaces the old string value with a brand new one
        }

        for(int x = 0; x < noWordsYet.length; x++){
            System.out.println(noWordsYet[x]);
        }

    }
}
