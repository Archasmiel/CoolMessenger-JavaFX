package net.user.client.ui.login;

import com.google.gson.Gson;
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
import net.user.client.ClientSocketService;
import net.user.client.StageService;
import net.user.client.websocket.ClientWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class LoginUI {

    @Autowired
    private ClientSocketService socketService;
    @Autowired
    private StageService stageService;
    @Autowired
    private Gson gson;

    @FXML private TextField ipAddressField;
    @FXML private TextField portField;
    @FXML private TextField nicknameField;
    @FXML private PasswordField passwordField;
    @FXML private Button connectButton;
    @FXML public Label statusText;

    @FXML
    public void initialize() {
        connectButton.setDefaultButton(true);
    }

    public void connectToServer(ActionEvent actionEvent) {
        String ipAddress = ipAddressField.getText();
        String port = portField.getText();
        String nickname = nicknameField.getText();
        String password = passwordField.getText();

        if (ipAddress.isEmpty()
                || port.isEmpty()
                || nickname.isEmpty()
                || password.isEmpty()) {
            statusText.setText("Please fill in all fields.");
            return;
        }

        LoginThread thread = new LoginThread(
            socketService, stageService, gson, statusText,
                ipAddress, port, nickname, password
        );
        thread.start();
    }
}
