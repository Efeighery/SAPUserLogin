module com.example.userlog {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires mysql.connector.j;
    requires java.sql;


    opens com.example.userlog to javafx.fxml;
    exports com.example.userlog;
}