package net.user.client.ui.login;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import net.user.client.Main;
import net.user.client.request.LoginData;
import net.user.client.ui.messenger.MessengerUI;
import net.user.client.websocket.ClientWebSocket;

import java.io.IOException;

public class LoginUI {

    public static final String MAIN_UI = "/login.fxml";
    private static ClientWebSocket clientSocket;

    @FXML
    private TextField ipAddressField;
    @FXML
    private TextField portField;
    @FXML
    private TextField nicknameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button connectButton;
    @FXML
    public Label statusText;

    public void show(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(MAIN_UI));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

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
        connectButton.setDefaultButton(true);
    }

    public static ClientWebSocket getClientSocket() {
        if (clientSocket == null) {
            throw new IllegalStateException("ClientWebSocket is not initialized.");
        }
        return clientSocket;
    }

    public void connectToServer(ActionEvent actionEvent) {
        String ipAddress = ipAddressField.getText();
        String port = portField.getText();
        String nickname = nicknameField.getText();
        String password = passwordField.getText();

        if (ipAddress.isEmpty() || port.isEmpty() || nickname.isEmpty() || password.isEmpty()) {
            statusText.setText("Please fill in all fields.");
            return;
        }

        new Thread(() -> {
            try {
                // Show initial status message
                Platform.runLater(() -> statusText.setText("Trying to connect to server..."));

                clientSocket = new ClientWebSocket(ipAddress, port);
                clientSocket.connect();

                int attempts = 0;
                int maxAttempts = 50;

                // Try to open the connection
                while (!clientSocket.isOpen() && attempts < maxAttempts) {
                    int currentAttempt = attempts + 1;

                    Platform.runLater(() -> statusText
                            .setText("Connecting... Attempt " +
                                    currentAttempt + "/" + maxAttempts));

                    Thread.sleep(200); // Give time for UI to render
                    attempts++;
                }

                if (!clientSocket.isOpen()) {
                    clientSocket.close();
                    clientSocket = null;

                    Platform.runLater(() -> statusText.setText("Connection failed!"));
                    return;
                }

                Platform.runLater(() -> statusText.setText("Connected to server!"));

                // Send login data
                LoginData data = new LoginData(nickname, password);
                clientSocket.send(data.toJson());

                // Wait for messenger to be ready
                attempts = 0;
                while (!clientSocket.canOpenMessenger() && attempts < maxAttempts) {
                    int currentAttempt = attempts + 1;

                    Platform.runLater(() -> statusText
                            .setText("Waiting for login response... Attempt "
                                    + currentAttempt + "/" + maxAttempts));

                    Thread.sleep(200);
                    attempts++;
                }

                if (!clientSocket.canOpenMessenger()) {
                    clientSocket.close();
                    clientSocket = null;

                    Platform.runLater(() -> statusText.setText("Login failed!"));
                    return;
                }

                Platform.runLater(() -> {
                    statusText.setText("Login successful!");
                    Main.loadUI(stage -> new MessengerUI(nickname).show(stage));
                });

            } catch (Exception e) {
                e.printStackTrace();
            }
        }).start();

    }
}
