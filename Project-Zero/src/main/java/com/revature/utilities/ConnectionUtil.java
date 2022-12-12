package com.revature.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/*
 * This ConnectionUtil class contains the method needed to create a connection with your database
 * It requires a properties file with the necessary data to connect to your database in order to work.
 */

public class ConnectionUtil {
    
    public static Connection createConnection(){
        try {
            // this creates a Java object to represent our properties
            Properties prop = new Properties();
            // this loads the properties in the properties file into the Java object
            prop.load(new FileInputStream("src/main/resources/db-properties.properties"));
            // we make use of the property values in the object created above to provide Java with the necessary
            // information to connect to our database
            return DriverManager.getConnection(
                prop.getProperty("URL"), 
                prop.getProperty("USERNAME"), 
                prop.getProperty("PASSWORD")
            );
        } catch (SQLException | IOException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public static void main(String[] args) {
        // use this to confirm you set up your environment variables correctly
        System.out.println(createConnection());
    }

}
