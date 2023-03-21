package com.example.userlog;
import java.sql.Connection;
import java.sql.DriverManager;

/*
 *  Class name: DatabaseConnection.java
 *
 *  Date e.g. 14/02/2023
 *
 * @author Eoghan Feighery, x19413886
 *
 */

/*
 *
 * @reference:  https://www.youtube.com/watch?v=DH3dWzmkT5Y/DatabaseConnection.java
 *
 */
public class DatabaseConnection {

    // This will be used to establish a session with the MySQL file and account table
    public Connection databaseLinks;

    public Connection getConnection(){
        // The database name, user and password is used to help the connection work
        String databaseName = "demodb";
        String databaseUser = "root";
        String databasePassword = "roottrial";


        // The database will be connected with this link
        String url = "jdbc:mysql://localhost/" + databaseName;

        try{
            // This variable will help connect the database to the application with the 3 main parameters (password, url link and database user)
            Class.forName("com.mysql.cj.jdbc.Driver");
            databaseLinks = DriverManager.getConnection(url, databaseUser, databasePassword);
        }
        // If an error happens the catch method will throw an exception
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }
        // The Connection/session variable is then returned to the user
        return databaseLinks;
    }

}
