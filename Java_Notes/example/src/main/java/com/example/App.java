package com.example;

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
    public static void main( String[] args ){
        Javalin app = Javalin.create();

        app.get("/hello", ctx -> ctx.result("Hello world!"));

        app.start();
    }
}
