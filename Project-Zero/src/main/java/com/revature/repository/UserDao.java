package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.models.User;
import com.revature.models.UsernamePasswordAuthentication;
import com.revature.utilities.ConnectionUtil;

public class UserDao {
    
    public User getUserByUsername(String username){
        try(Connection connection = ConnectionUtil.createConnection()) {
            String sql = "select * from users where username = ?";
            PreparedStatement ps = connection.prepareStatement(sql);
            ps.setString(1, username);
            ResultSet rs = ps.executeQuery();
            rs.next();
            User user = new User();
            user.setId(rs.getInt(1));
            user.setUsername(rs.getString(2));
            user.setPassword(rs.getString(3));
            return user;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return new User();
        }
    }

    public User createUser(UsernamePasswordAuthentication registerRequest){
        /*
         * we will be making use of a try-with-resource block. This is a convinience provided starting in java
         * 8 that allows us to "close" resources we open without having to explicitly write the code to
         * do so. This is a necessary action for our connection object we will be creating because our database can 
         * only handle so many open connections at a time.
         */

        /*
         * 1. we need to write our sql query (make a string to represent the query)
         * 2. we need to provide any relevant information to our query (add username and password)
         * 3. we need to execute the query
         * 4. we need to handle any sort of response we get
         */
        try(Connection connection = ConnectionUtil.createConnection()) {
            // 1. craft the query
            String sql = "insert into users values (default,?,?)"; // ? are placeholders for info we will provide
            /*
             * we need to provide the prepareStatement method with the sql to be executed, and because we
             * are returning a User object in the overall method we need to get the generated id from
             * the user that is created
             */
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            // 2. provide relevant information
            /*
             * keep in mind, when working with JDBC and entering/retrieving info indexing starts at 1, not at 0
             */
            ps.setString(1, registerRequest.getUsername()); // set username first because of order in table
            ps.setString(2, registerRequest.getPassword()); // set password second
            // 3. execute query
            ps.execute(); // execute the statement
            ResultSet rs = ps.getGeneratedKeys(); // get the generated id and save it in a result set
            // 4. handle the results
            User newUser = new User();
            rs.next(); // anytime you need to get info from a result set you must call next() once
            int newId = rs.getInt("id");
            newUser.setId(newId);
            newUser.setUsername(registerRequest.getUsername());
            newUser.setPassword(registerRequest.getPassword());
            return newUser;
        } catch (SQLException e) {
            System.out.println(e.getMessage()); // perhaps log this info in your projects?
            return new User();
        }
    }
    // use a main method to test your work
    public static void main(String[] args) {
        UserDao dao = new UserDao();
        UsernamePasswordAuthentication newUser = new UsernamePasswordAuthentication();
        newUser.setUsername("Java"); // make sure the username is unique
        newUser.setPassword("I want Spring");
        // System.out.println(dao.createUser(newUser).getId());
        System.out.println(dao.getUserByUsername("Java"));
    }

}
