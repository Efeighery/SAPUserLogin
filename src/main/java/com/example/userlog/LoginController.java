package com.example.userlog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/*
 *  Class name: LoginController.java
 *
 *  Date e.g. 14/02/2023
 *
 * @author Eoghan Feighery, x19413886
 *
 */

/*
 *
 * @reference:  https://www.youtube.com/watch?v=DH3dWzmkT5Y/LoginController.java
 *
 */

public class LoginController {

    // The FXML variables are declared here
    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMsg;
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordTF;

    public void loginButtonOnAction(ActionEvent event){

        // This method will verify if the user exists in the MySQL table
        // If the text fields are blank then the verification method will trigger
        if(usernameTF.getText().isBlank() == false && passwordTF.getText().isBlank() == false){
            verifyLogin();
        }
        else{
            // Or this message will appear instead
            loginMsg.setText("All fields need to be filled in");
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        // If the cancel button is activated, the entire application will shut down
        Stage stage = (Stage) cancelButton.getScene().getWindow();

        stage.close();
    }

    public void verifyLogin(){
        // Here, this will check in the database table if a logged-in user is inside the table
        // The DatabaseConnection class will be used to activate a session
        DatabaseConnection connector = new DatabaseConnection();
        Connection connectDB = connector.getConnection();

        // This MySQL statement is used to check the table to look for a confirmed user that logged in
        String confirmLog = "SELECT count(1) FROM account_info WHERE username = '"+usernameTF.getText()+"' and password = '"+passwordTF.getText()+"'";

        try {
            // This try catch method will be used to enter the Account Registration page
            // If successful the Result statement is used to help grant users access to the account creation page if a specified user exists
            Statement statement = connectDB.createStatement();
            ResultSet queryOut = statement.executeQuery(confirmLog);

            while(queryOut.next()){
                // In this while loop if the statement rings true then the createAccountPhase method will be activated
                if(queryOut.getInt(1) == 1){
                    // loginMsg.setText("Welcome aboard!!!!!!");
                    createAccountPhase();
                }
                else{
                    // Otherwise, this message will show up instead
                    loginMsg.setText("Invalid criteria. Please try again");
                }
            }
        }
        catch(Exception e){
            // Or if it runs into an error, this part of the try catch will trigger
            e.printStackTrace();
            e.getCause();
        }

    }

    public void createAccountPhase(){
        try{
            // This method will go from the login page to the register page if the verified login is successful
            FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource("registration.fxml"));

            Stage registerStage = new Stage();
            Scene scene = new Scene(fxmlLoader.load(),
                    620,
                    500);
            registerStage.setTitle("Hello!");
            registerStage.setScene(scene);
            registerStage.show();
        }
        catch (Exception e){
            e.printStackTrace();
            e.getCause();
        }
    }

}