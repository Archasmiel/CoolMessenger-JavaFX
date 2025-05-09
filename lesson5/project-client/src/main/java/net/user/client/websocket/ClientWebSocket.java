package net.user.client.websocket;

import com.google.gson.JsonObject;
import net.user.client.Main;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

public class ClientWebSocket extends WebSocketClient {

    private boolean canOpenMessenger;

    public ClientWebSocket(String ip, String port) throws URISyntaxException {
        super(new URI("ws://" + ip + ":" + port));
        canOpenMessenger = false;
    }

    @Override
    public void onOpen(ServerHandshake handshakedata) {
        System.out.println("Connected to server: " + getURI());
        send("Hello, server!");
    }

    @Override
    public void onMessage(String message) {
        JsonObject json = Main.GSON.fromJson(message, JsonObject.class);
        int code = json.get("code").getAsInt();
        switch (code) {
            case 1 -> {
                canOpenMessenger = true;
            }
            case 2 -> {
                canOpenMessenger = false;
            }
            default -> {
                System.out.println("Unknown message code: " + code);
            }
        }
    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        System.out.println("Connection closed: " + getURI());
    }

    @Override
    public void onError(Exception ex) {
        ex.printStackTrace();
    }

    public boolean canOpenMessenger() {
        return canOpenMessenger;
    }
}
