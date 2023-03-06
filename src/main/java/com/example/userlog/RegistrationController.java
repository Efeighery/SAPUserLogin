package com.example.userlog;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

import java.io.File;
import java.net.URL;
import java.sql.Connection;
import java.sql.Statement;

public class RegistrationController {

    @FXML
    private Button leaveButton;

    @FXML
    private TextField firstnameTF;

    @FXML
    private TextField lastnameTF;

    @FXML
    private TextField usernameTF;

    @FXML
    private Label registrationMsg;
    @FXML
    private Label confirmMsg;

    @FXML
    private PasswordField passwordTF;

    @FXML
    private PasswordField confirmPasswordTF;

    public void leaveButtonOnAction(ActionEvent event){
        Stage stage = (Stage) leaveButton.getScene().getWindow();
        stage.close();


        Platform.exit();
    }

    public void registerButtonOnAction(ActionEvent event){
        if(passwordTF.getText().equals(confirmPasswordTF.getText())){
            registerUser();
            confirmMsg.setText("");

        }
        else{
            confirmMsg.setText("Passwords aren't matching. Please check");
        }
    }

    public void registerUser(){
        DatabaseConnection connector = new DatabaseConnection();

        Connection connectDB = connector.getConnection();

        String firstname = firstnameTF.getText();
        String lastname = lastnameTF.getText();
        String username = usernameTF.getText();
        String password = passwordTF.getText();

        String insertField = "INSERT INTO account_info (firstname, lastname, username, password) VALUES ('";
        String insertValue = firstname + "', '"+ lastname + "', '"+ username + "', '"+ password+ "')";

        String insertToRegistration = insertField + insertValue;

        try{
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegistration);

            registrationMsg.setText("User registration confirmed");
        }
        catch (Exception ex){
            ex.printStackTrace();
            ex.getCause();
        }
    }

}
