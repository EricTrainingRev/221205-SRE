package control;
public class ControlFlowTwo {
    public static void main(String[] args) {
        /*
         * often you will find yourself needing to execute one of many potential pieces of code depending on 
         * the data your code is working with. Going back to the idea of getting a visa for your birthday, if 
         * you don't have enough money on the card to make a purchase you might find it helpful to get back the
         * actual value on the card
         */

         int visa = 50;
         int cost = 50;

         if(visa >= cost){
            System.out.println("congrats on your purchase");
         } else {
            System.out.println("You do not have enough money to make this purchase");
         }

         /*
          * the above example is useful for an either/or situation, but we can go further and add even more logical
          checks to our code
          */

        // only one of the print messages below will go to the console, depending on which (if any) of the if statements
        // has its condition satisfied first
        if(visa > cost){
            System.out.println("congrats on your purchase");
        } else if(visa == cost){
            System.out.println("Purchase made, you have no money left on the gift card");
        } else {
            System.out.println("You do not have enough money to make this purchase");
        }

        // one place you might make use of this is with user authentication
        String username = "username";
        String password = "passw0rd";

        // nested if statements can be a little rough to read: I recommend combining them into one if statement
        // username.equals("username") is the String equivalent of username == "username"

        // if you want to combine logical operations you can either use the logical and && or use the logical
        // orr || operators
        if(username.equals("username")){
            if(password.equals("passw0rd")){
                System.out.println("logged in successfully");
            } else {
                System.out.println("login failed: please try again");
            }
        } else {
            System.out.println("login failed: please try again");
        }

        if (username.equals("username") && password.equals("passw0rd")){
            System.out.println("logged in successfully");
        } else {
            System.out.println("login failed: please try again");
        }




    }
}
