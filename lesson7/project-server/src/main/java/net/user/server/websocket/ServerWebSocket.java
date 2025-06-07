package net.user.server.websocket;

import com.google.gson.Gson;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.java_websocket.WebSocket;
import org.java_websocket.handshake.ClientHandshake;
import org.java_websocket.server.WebSocketServer;

import java.net.InetSocketAddress;

@Slf4j
public class ServerWebSocket extends WebSocketServer {

    @Setter
    private Gson gson;

    public ServerWebSocket(String ip, int port) {
        super(new InetSocketAddress(ip, port));
    }

    @Override
    public void onStart() {
        log.info("Server started on {}:{}",
                getAddress().getHostString(),
                getAddress().getPort());
    }

    @Override
    public void onOpen(WebSocket conn, ClientHandshake handshake) {
        log.info("New connection from {}",
                conn.getRemoteSocketAddress());
    }

    @Override
    public void onClose(WebSocket conn, int code, String reason, boolean remote) {
        log.info("Connection closed: {} Reason: {}",
                conn.getRemoteSocketAddress(), reason);
    }

    @Override
    public void onMessage(WebSocket conn, String message) {
        log.info("Received: {}", message);
    }

    @Override
    public void onError(WebSocket conn, Exception ex) {
        log.error("Error: {}", ex.getMessage());
    }

}