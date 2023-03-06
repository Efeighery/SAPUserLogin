package com.example.userlog;

import java.sql.Connection;
import java.sql.DriverManager;
public class DatabaseConnection {
    public Connection databaseLinks;

    public Connection getConnection(){
        String databaseName = "demodb";
        String databaseUser = "root";
        String databasePassword = "roottrial";

        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLinks = DriverManager.getConnection(url, databaseUser, databasePassword);
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        return databaseLinks;
    }

}
