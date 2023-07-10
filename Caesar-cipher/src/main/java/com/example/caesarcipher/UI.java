package com.example.caesarcipher;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.nio.file.*;

public class UI extends Application
{
    public static Path path = Paths.get("src"); // Setting up the path for icons etc...
    /*
    Here we add menu elements. Where we include our input for it to be encoded or decoded
    the shift amount and the buttons
     */
    private TextArea plainText = new TextArea();
    private TextArea cipherText = new TextArea();
    private TextArea results = new TextArea();
    private TextField plainKey = new TextField();
    private TextField cipherKey = new TextField();
    private Button encrypt = new Button("Encrypt");
    private Button decrypt = new Button("Decrypt");
    private CaesarCipher caesar = new CaesarCipher(); // Our cipher class

    @Override
    public void start(Stage stage) throws Exception
    {
        // Here we set a gridpane to include our elements
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(10));
        gridPane.setHgap(5);
        gridPane.setVgap(5);

        // Here is our "Plain Text" area
        plainText.setMaxWidth(300);
        plainText.setMaxHeight(300);
        gridPane.add(new Label("Plain Text"), 0, 0);
        gridPane.add(plainText, 0,1);

        // Here is our "Encrypted Text" area
        cipherText.setMaxWidth(300);
        cipherText.setMaxHeight(300);
        gridPane.add(new Label("Encrypted Text"), 1, 0);
        gridPane.add(cipherText, 1,1);

        // Here is our "Results" area
        results.setMaxHeight(300);
        results.setMaxWidth(300);
        gridPane.add(new Label("Results"), 2, 0);
        gridPane.add(results, 2, 1);

        // Here is our shift amount for encryption
        plainKey.setMaxWidth(50);
        gridPane.add(new Label("Key"), 0, 2);
        gridPane.add(plainKey, 0,3);

        // Here is out shift amount for decryption
        cipherKey.setMaxWidth(50);
        gridPane.add(new Label("Key"), 1, 2);
        gridPane.add(cipherKey, 1,3);

        // Here are out buttons
        gridPane.add(encrypt,0,4);
        gridPane.add(decrypt, 1,4);

        // Some info regarding the program
        gridPane.add(new Label("Note: Make sure that the shift amount is between 0 and 26!"), 0, 6);
        gridPane.add(new Label( "Other forms of encryption will be added hopefully -so7en"), 2, 6);

        /*
        Here we have both how we set up our encryption and decryption
        the program will get the number that was provided as the shift amount
        and will send it to the function that encrypts the text we provided then the result is
        outputted unto the "Results" textarea
        same goes for our decryption method
         */
        encrypt.setOnAction(event ->{
            int key = Integer.parseInt(plainKey.getText()); // Will get the shift amount
            String encode = this.caesar.encrypt(plainText.getText(), key); // Will get the encrypted text
            results.setText(encode);
            results.getText();
        });
        decrypt.setOnAction(event ->{
            int key = Integer.parseInt(cipherKey.getText()); // Will get the shift amount
            String decode = this.caesar.decrypt(cipherText.getText(), key); // Will get the decrypted text
            results.setText(decode);
            results.getText();
        });

        // Our background is set here
        BackgroundFill backgroundFill = new BackgroundFill(Color.SKYBLUE, null, null);
        Background background = new Background(backgroundFill);
        gridPane.setBackground(background);

        Scene scene = new Scene(gridPane, 1000, 250);
        stage.getIcons().add(new Image(path.toAbsolutePath() + "\\icon.jpg")); // Icon for the program
        stage.setTitle("Caesar Cipher");
        stage.setScene(scene);
        stage.show();

    }

    public static void main(String[] args) {
        launch();
    }
}