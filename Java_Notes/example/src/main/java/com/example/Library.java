package com.example;

import java.util.ArrayList;
import java.util.List;

import io.javalin.Javalin;

public class Library {

    /*
     * notice I did not add an access modifier: this means the variable is given the default
     * access modifier, which means any code within the current package can use it
     */
    static List<Book> library = new ArrayList<>();

    public static void main(String[] args) {
        Book startingBook = new Book();
        startingBook.setTitle("The Fellowship of the Ring");
        startingBook.setAuthor("J. R. R. Tolkien");
        startingBook.setGenre("Fantasy");
        startingBook.setIsbn(123456789);

        library.add(startingBook);

        Javalin app = Javalin.create();

        app.get("/book/{index}", ctx -> {
            // here you can see I don't HAVE to make the String variable first, I can pass the results of
            // the pathParam method directly into the parseInt method
            int index = Integer.parseInt(ctx.pathParam("index"));
            // we get the book
            Book book = library.get(index);
            // we let
            ctx.json(book);
            ctx.status(200);
        });

        app.post("/book", ctx ->{
            // convert the book json in the http request body into a Java Book object
            Book newBook = ctx.bodyAsClass(Book.class);
            // add the new book into the library
            library.add(newBook);
            // tell Javalin to return a success message
            ctx.result("Book added to the library!");
            ctx.status(201);
        });

        app.patch("/book/{index}", ctx -> {
            int index = Integer.parseInt(ctx.pathParam("index"));
            Book updatedBook = ctx.bodyAsClass(Book.class);
            library.get(index).setAuthor(updatedBook.getAuthor());
            library.get(index).setTitle(updatedBook.getTitle());
            library.get(index).setGenre(updatedBook.getGenre());
            // since isbn numbers be unique across books I will not write code to edit the isbn
            ctx.json(library.get(index));
            ctx.status(200);
        });

        app.put("/book/{index}", ctx -> {
            int index = Integer.parseInt(ctx.pathParam("index"));
            Book updatedBook = ctx.bodyAsClass(Book.class);
            library.set(index, updatedBook);
            ctx.json(library.get(index));
            ctx.status(200);
        });

        app.delete("/book/{index}", ctx -> {
            int index = Integer.parseInt(ctx.pathParam("index"));
            library.remove(index);
            ctx.status(200);
        });

        app.start();

    }
    
}
