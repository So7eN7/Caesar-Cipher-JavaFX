module com.example.caesarcipher {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.caesarcipher to javafx.fxml;
    exports com.example.caesarcipher;
}