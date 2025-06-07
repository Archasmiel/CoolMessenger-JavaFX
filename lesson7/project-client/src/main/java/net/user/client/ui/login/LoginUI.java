package net.user.client.ui.login;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import net.user.client.ClientSocketService;
import net.user.client.StageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class LoginUI {

    private final ClientSocketService socketService;
    private final StageService stageService;
    private final Gson gson;

    @FXML private TextField ipAddressField;
    @FXML private TextField portField;
    @FXML private TextField nicknameField;
    @FXML private PasswordField passwordField;
    @FXML private Button connectButton;
    @FXML public Label statusText;

    @Autowired
    public LoginUI(
            ClientSocketService socketService,
            StageService stageService,
            Gson gson
    ) {
        this.socketService = socketService;
        this.stageService = stageService;
        this.gson = gson;
    }

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
