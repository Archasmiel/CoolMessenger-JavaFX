package net.user.client;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.function.Function;

public class LoginUI {

    public static final String MAIN_UI = "/login.fxml";

    @FXML private TextField ipAddressField;
    @FXML private TextField portField;
    @FXML private TextField nicknameField;
    @FXML private PasswordField passwordField;
    @FXML private Button connectButton;

    public void show(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(MAIN_UI));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Function<Integer, String> func = (num) -> Integer.toString(num);

        // Scene and stage setup
        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Login...");
        stage.setScene(scene);
        stage.setMinWidth(400);
        stage.setMinHeight(300);
        stage.show();
    }

    @FXML
    public void initialize() {
        connectButton.setDefaultButton(true);   // Зробити основною кнопку
    }

}
