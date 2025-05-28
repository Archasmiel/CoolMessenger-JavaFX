package net.user.client.ui.login;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.application.Platform;
import javafx.scene.control.Label;
import lombok.extern.slf4j.Slf4j;
import net.user.client.ClientSocketService;
import net.user.client.StageService;
import net.user.client.request.LoginData;

import java.net.URISyntaxException;

@Slf4j
public class LoginThread extends Thread {

    private static final int TIMEOUT = 100;
    private static final int MAX_ATTEMPTS = 50;

    private final ClientSocketService socketService;
    private final StageService stageService;
    private final Gson gson;

    private final Label statusText;
    private final String ipAddress;
    private final String port;
    private final String nickname;
    private final String password;

    private int attempts;

    public LoginThread(ClientSocketService socketService,
                       StageService stageService,
                       Gson gson,
                       Label statusText,
                       String ipAddress, String port,
                       String nickname, String password) {
        this.socketService = socketService;
        this.stageService = stageService;
        this.gson = gson;
        this.statusText = statusText;
        this.ipAddress = ipAddress;
        this.port = port;
        this.nickname = nickname;
        this.password = password;
    }

    @Override
    public void run() {
        try {
            connectToServer();
            sendLoginData();
            waitForLogin();
        } catch (Exception e) {
            log.error("Error", e);
        }
    }

    private void connectToServer()
            throws InterruptedException, URISyntaxException {
        // Приєднання до сервера
        Platform.runLater(() -> statusText.setText("Trying to connect to server..."));
        socketService.connect(ipAddress, port);

        // Очікування з'єднання...
        attempts = 0;
        while (!socketService.isOpen() && attempts < MAX_ATTEMPTS) {
            attempts++;
            Platform.runLater(() -> statusText
                    .setText("Connecting... Attempt " +
                            attempts + "/" + MAX_ATTEMPTS));

            Thread.sleep(TIMEOUT);
        }

        if (socketService.isOpen()) {
            Platform.runLater(() -> statusText.setText("Connected to server!"));
        } else {
            Platform.runLater(() -> statusText.setText("Connection failed!"));
            socketService.close();
        }
    }

    private void sendLoginData() {
        // Якщо наш сокет пустий - значить ми не приєдналися
        if (socketService.socketNull()) return;

        // Відправимо дані для логіну серверу
        LoginData data = new LoginData(nickname, password);
        JsonObject jsonData = data.toJson();
        String dataMsg = gson.toJson(jsonData);
        socketService.send(dataMsg);
    }

    private void waitForLogin()
            throws InterruptedException {
        // Нема сенсу чекати логін, якщо сокет пустий
        if (socketService.socketNull()) return;

        // Чекаємо на відповідь, що ми зайшли успішно
        attempts = 0;
        while (!socketService.canOpenMessenger() && attempts < MAX_ATTEMPTS) {
            attempts++;
            Platform.runLater(() -> statusText
                    .setText("Waiting for login response... Attempt "
                            + attempts + "/" + MAX_ATTEMPTS));

            Thread.sleep(TIMEOUT);
        }

        if (socketService.canOpenMessenger()) {
            Platform.runLater(() -> {
                statusText.setText("Login successful!");
                stageService.loadMessengerUI();
            });
        } else {
            Platform.runLater(() -> statusText.setText("Login failed!"));
            socketService.close();
        }
    }

}
