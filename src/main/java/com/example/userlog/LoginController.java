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


public class LoginController {

    @FXML
    private Button cancelButton;
    @FXML
    private Label loginMsg;
    @FXML
    private TextField usernameTF;
    @FXML
    private PasswordField passwordTF;

    public void loginButtonOnAction(ActionEvent event){

        if(usernameTF.getText().isBlank() == false && passwordTF.getText().isBlank() == false){
            verifyLogin();
        }
        else{
            loginMsg.setText("All fields need to be filled in");
        }
    }

    public void cancelButtonOnAction(ActionEvent event){
        Stage stage = (Stage) cancelButton.getScene().getWindow();

        stage.close();
    }

    public void verifyLogin(){
        DatabaseConnection connector = new DatabaseConnection();
        Connection connectDB = connector.getConnection();

        String confirmLog = "SELECT count(1) FROM account_info WHERE username = '"+usernameTF.getText()+"' and password = '"+passwordTF.getText()+"'";

        try {
            Statement statement = connectDB.createStatement();
            ResultSet queryOut = statement.executeQuery(confirmLog);

            while(queryOut.next()){
                if(queryOut.getInt(1) == 1){
                    // loginMsg.setText("Welcome aboard!!!!!!");
                    createAccountPhase();
                }
                else{
                    loginMsg.setText("Invalid criteria. Please try again");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
            e.getCause();
        }

    }

    public void createAccountPhase(){
        try{
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