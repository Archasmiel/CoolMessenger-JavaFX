package net.user.client.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import javafx.application.Platform;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

@Slf4j
public class ClientWebSocket extends WebSocketClient {

    @Getter
    private boolean canOpenMessenger;
    @Setter
    private Gson gson;

    public ClientWebSocket(
            String ip, String port
    ) throws URISyntaxException {
        super(new URI("ws://" + ip + ":" + port));
        canOpenMessenger = false;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        log.info("Connected to server: {}", getURI());
        send("Hello, server!");
    }

    @Override
    public void onMessage(String message) {
        JsonObject json = gson.fromJson(message, JsonObject.class);
        int code = json.get("code").getAsInt();
        switch (code) {
            case 1 -> canOpenMessenger = true;
            case 2 -> canOpenMessenger = false;
            default -> log.info("Unknown message code: {}", code);
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        log.info("Connection closed: {}", getURI());
        Platform.exit();
    }

    @Override
    public void onError(Exception ex) {
        log.error("{}", ex.getMessage());
    }
}
