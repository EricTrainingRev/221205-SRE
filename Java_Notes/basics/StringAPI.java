package basics;
public class StringAPI {
    /*
     * Strings are the simplest object we can use to represent words/phrases within our code. They are
     * useful when we need to do things like pass messages to our users, create error messages for developers, etc.
     */

    /*
     * Strings are immutable objects: at best, you can reasign a variable with your "updated" string, but you
     * do not change the initial string that was created
     */

    public static void main(String[] args) {
        String myName = "Eric";
        System.out.println(myName);

        myName+= " Suminski"; // a new string object is being created here, and myName has its reference changed to the brand new string

        System.out.println(myName);

        String[] splitMyName = myName.split(" "); // this method will break apart the string and place in an array the individual parts
        for(int x = 0; x < splitMyName.length; x++){
            System.out.println(splitMyName[x]);
        }

        // I've seen something like this in many coding challenges
        String numbers = "10 7 15 24 5";
        // challenge would be something like "find the numbers and add them together"



        /*
         * So this begs the question, if you want to change your word/phrase, what tool would you use to do so? The easiest tool to use
         * would be the StringBuilder class
         */

         StringBuilder myWord = new StringBuilder(myName);

         System.out.println(myWord);

        // myWord.reverse();
        myWord.append(" the bold!");
        System.out.println(myWord);

        // Stringbuilder does not have a built in split method, but it is easy to convert your Stringbuilder back into a string
        myWord.toString().split(" "); // this is an easy way to split your stringbuilder


        /*
         * Use a regular string if you know you will not need to perform many (if any) operations on your word/phrase
         * Use StringBuilder when you know you will need to make significant changes to your word/phrase
         */



    }
}
