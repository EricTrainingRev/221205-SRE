package com.example;

import java.util.ArrayList;
import java.util.List;

import io.javalin.Javalin;

/*
 * All maven projects have their code stored inside of a src/main/java directory. Now, the reason I also have
 * a "com" package and inside of that one there is a package called "example" is because the maven project generator
 * names your base package structure after the groupId you set when you set up your project. What this means is 
 * that all of your java code is going to, at a minimum, have a "package com.example" to start it off in order
 * to let maven know where the code is when it builds our application
 */

public class App 
{
    public static List<String> names = new ArrayList<>();
    public static void main( String[] args ){

        // adding some names to the names list so we can interact with them via http
        names.add("Billy");
        names.add("Sally");
        names.add("Bruce");


        Javalin app = Javalin.create();

        /*
         * the code below tells Javalin that our web server that is created needs to be able to handle a
         * GET http request that ends with /hello. Upon our application recieving this request the application 
         * should return the text "Hello world!" to the requester
         */
        app.get("/hello", ctx -> ctx.result("Hello world!"));

        app.post("/add", ctx -> {
            // we get the name from the http request body and save it to a variable
            String newName = ctx.body();
            // we add the new name into the list of names
            names.add(newName);
            // we tell Javalin we want to return a String message to the requester
            ctx.result("Congrats! You added a new name to the names database");
            // we tell Javalin we want the status code 201 returned in the response
            ctx.status(201);
        });

        app.get("/person/{num}", ctx -> {
            // we use the pathParam method to get the value of the num path parameter
            String numAsString = ctx.pathParam("num");
            // since the value is a number, we convert the string into an int
            int num = Integer.parseInt(numAsString);
            // we use our newly converted int to access the index position of the name we want
            String person = names.get(num);
            // we tell javalin to return the name of the person we got
            ctx.result(person);
            // we tell Javalin to set the status code to 200
            ctx.status(200);
        });

        // this is an example of how you could inform the user that they requested information that does not exist
        app.get("/protectedGet/{num}", ctx ->{
            String numAsString = ctx.pathParam("num");
            int num = Integer.parseInt(numAsString);
            try {
                String person = names.get(num);
                ctx.result(person);
                ctx.status(200);
            } catch (IndexOutOfBoundsException e) {
                ctx.result("No person found at that index position");
                ctx.status(404);
            }
        });

        // use put when you want to replace EVERYTHING in the resource
        app.put("/replace/{num}", ctx -> {
            // get the index position from the path as a string
            String numAsString = ctx.pathParam("num");
            // convert string index position to an int
            int num = Integer.parseInt(numAsString);
            // get the string value we will be replacing the current data with
            String replacePerson = ctx.body();
            // use the set method to replace the old name with the new name
            names.set(num, replacePerson);
            // tell Javalin to give a success message and status code back to the user
            ctx.result("Replaced person successfully!");
            ctx.status(200);
        });

        //
        app.patch("/update/{num}", ctx -> {
            String numAsString = ctx.pathParam("num");
            int num = Integer.parseInt(numAsString);
            String updatedInformation = ctx.body();
            names.set(num, updatedInformation);
            ctx.result("Successfully updated the person's information");
            ctx.status(200);
        });

        app.delete("/delete", ctx -> {
            names.clear();
            ctx.result("All names have been removed from the database");
            ctx.status(200);
        });

        app.start();
    }
}
