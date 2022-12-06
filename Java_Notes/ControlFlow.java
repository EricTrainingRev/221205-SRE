public class ControlFlow {
    /*
     * When we are talking about control flow we are discussing the tools available to us to determine what
     * parts of our code execute, when they should execute, and the conditions under which they will execute
     */

     // reminder: you MUST have a main method if you want to execute your Java code
     public static void main(String[] args){
        int visaCard = 100; // this visaCard variable represents the 100$ you got for your birthday
        int burgerCost = 45; // this burgerCost variable represents the 45$ charge for the nice burger you want to get

        // the three code examples below will all achieve the same result: the if statement returns a logical true, so the code inside the 
        // if statements will execute
        if(visaCard >= burgerCost){
            System.out.println("Congrats on buying a burger! Gordon Ramsay is pleased to serve you today");
        }

        if(100 >= 45){
            System.out.println("Congrats on buying a burger! Gordon Ramsay is pleased to serve you today");
        }

        if (true){
            System.out.println("Congrats on buying a burger! Gordon Ramsay is pleased to serve you today");
        }




        // in the three examples below no message will print to the console because all the logical checks will return a false boolean

        int lowCard = 40; // you've used your card a couple of times now, so there is less money on it

        if(lowCard >= burgerCost){
            System.out.println("Congrats on buying a burger! Gordon Ramsay is pleased to serve you today");
        }

        if (40 >= 45){
            System.out.println("Congrats on buying a burger! Gordon Ramsay is pleased to serve you today");
        }

        if (false){
            System.out.println("Congrats on buying a burger! Gordon Ramsay is pleased to serve you today");
        }

        /*
         * So far we have just looked at the >= operator, but there are many other options we can use:
         * > greater than
         * < less than
         * >= greater or equal
         * <= less or equal
         * == equal
         */

         int numOne = 32;
         int numTwo = 32;

         if(numOne == numTwo){
            System.out.println("Will this phrase show?");
         }

         int newCard = 100;
         int pizzaCost = 5;

         // so far we have been looking at executing our code on a true statement
         if(newCard >= pizzaCost){
            System.out.println("Enjoy your pizza!");
         }

         // you can inverse your logic by using the not operator !

         // if you can inverse your logic without the not operator, then do so
         if(newCard < pizzaCost){
            System.out.println("No pizza for you: try an apple");
         }

         int balance = 75;
         int expectedBalance = 85;

         // by using the not operator we tell the if statement to trigger on a false statement instead of a true one
         if(balance != expectedBalance){
            System.out.println("where is my money!");
         }

         // this does in fact work: the not operator tells our code to execute the code inside the if statement if the boolean value provided is false
         if(!false){
            System.out.println("will this work?");
         }

         int myNum = 23;

         // we can use this to confirm a number is NOT another number
         if(myNum != 45){
            System.out.println("myNum does not equal 45");
         }

     }
}
