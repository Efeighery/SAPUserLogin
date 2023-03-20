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

    // All FXML variables are called through here
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
// When a user clicks on the leave button, the application window will close down
        Stage stage = (Stage) leaveButton.getScene().getWindow();
        stage.close();


        Platform.exit();
    }

    public void registerButtonOnAction(ActionEvent event){
        // This will be used to check the password and the password confirmation fields are matching with one another
        // If they're matching with each other, then the registerUser method will be activated
        if(passwordTF.getText().equals(confirmPasswordTF.getText())){
            registerUser();
            confirmMsg.setText("");
        }
        else{
            // Otherwise, if they're not matching, then this message will appear
            confirmMsg.setText("Passwords aren't matching. Please check");
        }
    }

    // This will be used to add a new user to the SQL table
    public void registerUser(){
        // The Database Instantiable will be called here
        // And will be initialised in a Connection session
        DatabaseConnection connector = new DatabaseConnection();
        Connection connectDB = connector.getConnection();

        // All the credentials will be grabbed from their respective text fields
        String firstname = firstnameTF.getText();
        String lastname = lastnameTF.getText();
        String username = usernameTF.getText();
        String password = passwordTF.getText();

        // These will be used to add a new user entry onto the MySQL table and will take the text field values to do so
        String insertField = "INSERT INTO account_info (firstname, lastname, username, password) VALUES ('";
        String insertValue = firstname + "', '"+ lastname + "', '"+ username + "', '"+ password+ "')";

        // Then this String will be used to combine the two above to confirm the entry inclusion
        String insertToRegistration = insertField + insertValue;

        try{
            // This SQL statement is used to return the value for the Database method
            // Then it will execute the new account entry addition.
            Statement statement = connectDB.createStatement();
            statement.executeUpdate(insertToRegistration);

            registrationMsg.setText("User registration confirmed");
        }
        catch (Exception ex){
            // Otherwise, this will trigger should an error occur.
            ex.printStackTrace();
            ex.getCause();
        }
    }

}
