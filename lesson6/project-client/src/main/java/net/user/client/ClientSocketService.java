package net.user.client;

import com.google.gson.Gson;
import net.user.client.websocket.ClientWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public class ClientSocketService {

    @Autowired
    private Gson gson;

    private ClientWebSocket socket;

    public void connect(
            String ip, String port
    ) throws URISyntaxException {
        socket = new ClientWebSocket(ip, port);
        socket.setGson(gson);
        socket.connect();
    }

    public void close() {
        if (socket != null) {
            socket.close();
            socket = null;
        }
    }

    public void send(String msg) {
        if (socket != null) {
            socket.send(msg);
        }
    }

    public boolean isOpen() {
        return socket.isOpen();
    }

    public boolean canOpenMessenger() {
        return socket.isCanOpenMessenger();
    }

}






















