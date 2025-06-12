package net.user.server.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.application.Platform;
import javafx.collections.ObservableList;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import net.user.server.AuthService;
import net.user.server.data.Client;
import net.user.server.request.*;
import net.user.server.ui.MessageBubble;
import net.user.server.ui.ServerConsole;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;
import org.w3c.dom.ls.LSOutput;

import java.net.InetSocketAddress;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Slf4j
public class ServerWebSocket extends WebSocketServer {

    public static final DateTimeFormatter FORMATTER =
            DateTimeFormatter.ofPattern("dd.MM.yyyy/HH:mm:ss");

    @Setter private Gson gson;
    @Setter private ObservableList<Client> clientList;
    @Setter private ServerConsole console;
    @Setter private AuthService authService;

    public ServerWebSocket(String ip, int port) {
        super(new InetSocketAddress(ip, port));
    }

    private MessageBubble signedMessage(String sender, String message) {
        String header = String.format("%s [%s]", sender,
                LocalDateTime.now().format(FORMATTER));
        MessageBubble bubble = new MessageBubble(header, message);
        Platform.runLater(() -> console.addMessage(bubble));
        return bubble;
    }

    private MessageBubble serverMessage(String msg) {
        return signedMessage("Server", msg);
    }

    @Override
    public void onStart() {
        String msg = String.format(
                "Server started on %s:%d",
                getAddress().getHostString(),
                getAddress().getPort()
        );
        serverMessage(msg);
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        String msg = String.format(
                "New connection from %s",
                conn.getRemoteSocketAddress());
        serverMessage(msg);
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        String msg = String.format(
                "Connection closed: %s Reason: %s",
                conn.getRemoteSocketAddress(), reason);
        serverMessage(msg);

        clientList.stream().filter(e -> e.getWebSocket() == conn)
                .findFirst().ifPresent(e ->
                        Platform.runLater(() -> clientList.remove(e)));
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        if (message == null
                || message.isBlank()
                || !message.contains("code")) return;
        String msg = String.format("Received: %s", message);
        signedMessage("Client", msg);

        JsonObject json = gson.fromJson(message, JsonObject.class);
        int code = json.get("code").getAsInt();

        if (code == 0) doLogin(conn, json);
        else
        if (code == 3) doRegister(conn, json);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        String msg = String.format("Error: %s", ex.getMessage());
        signedMessage("Client", msg);

        clientList.stream().filter(e -> e.getWebSocket() == conn)
                .findFirst().ifPresent(e ->
                        Platform.runLater(() -> clientList.remove(e)));
        conn.close();
    }

    private void doRegister(WebSocket conn, JsonObject json) {
        AbstractData data;
        String nickname = json.get("nickname").getAsString();
        String password = json.get("password").getAsString();

        if (authService.isUsernameTaken(nickname)) {
            data = new RegisterFailed();
        } else {
            data = new RegisterSuccess();
            authService.registerUser(nickname, password);
        }

        String msg = gson.toJson(data.toJson());
        conn.send(msg);
    }

    private void doLogin(WebSocket conn, JsonObject json) {
        AbstractData data;
        String nickname = json.get("nickname").getAsString();
        String password = json.get("password").getAsString();

        if (authService.validateCredentials(nickname, password)) {
            data = new LoginSuccess();
            Client client = new Client(
                    conn.getRemoteSocketAddress().getAddress().toString(),
                    conn.getRemoteSocketAddress().getPort(),
                    conn, nickname, password
            );
            Platform.runLater(() -> clientList.add(client));
        } else {
            data = new LoginFailed();
        }

        String msg = gson.toJson(data.toJson());
        conn.send(msg);
    }

}