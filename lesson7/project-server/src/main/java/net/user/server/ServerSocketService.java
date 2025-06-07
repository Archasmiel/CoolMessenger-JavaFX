package net.user.server;

import com.google.gson.Gson;
import net.user.server.websocket.ServerWebSocket;
import org.java_websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerSocketService {

    private final Gson gson;
    private ServerWebSocket socket;

    @Autowired
    public ServerSocketService(Gson gson) {
        this.gson = gson;
    }

    public synchronized void start(
            String ip, int port
    ) {
        socket = new ServerWebSocket(ip, port);
        socket.setGson(gson);
        socket.start();
    }

    public synchronized void close() {
        if (socket != null) {
            try {
                socket.stop();
            } catch (Exception e) {
                e.printStackTrace();
            }
            socket = null;
        }
    }

    public synchronized void send(WebSocket socket, String msg) {
        if (socket != null) {
            socket.send(msg);
        }
    }

    public synchronized boolean socketNull() {
        return socket == null;
    }

}
