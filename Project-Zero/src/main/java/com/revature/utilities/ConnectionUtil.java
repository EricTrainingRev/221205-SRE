package com.revature.utilities;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionUtil {
    
    public static Connection createConnection(){
        try {
            Properties prop = new Properties();
            prop.load(new FileInputStream("src/main/resources/db-properties.properties"));
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
