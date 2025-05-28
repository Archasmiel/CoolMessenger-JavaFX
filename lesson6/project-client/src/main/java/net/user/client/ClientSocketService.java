package net.user.client;

import com.google.gson.Gson;
import net.user.client.websocket.ClientWebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URISyntaxException;

@Service
public class ClientSocketService {

    private final Gson gson;
    private ClientWebSocket socket;

    @Autowired
    public ClientSocketService(Gson gson) {
        this.gson = gson;
    }

    // synchronized - лише один потік працює
    // з цим в даний час - черга на змінну/метод

    public synchronized void connect(
            String ip, String port
    ) throws URISyntaxException {
        socket = new ClientWebSocket(ip, port);
        socket.setGson(gson);
        socket.connect();
    }

    public synchronized void close() {
        if (socket != null) {
            socket.close();
            socket = null;
        }
    }

    public synchronized void send(String msg) {
        if (socket != null) {
            socket.send(msg);
        }
    }

    public synchronized boolean isOpen() {
        return socket.isOpen();
    }

    public synchronized boolean canOpenMessenger() {
        return socket.isCanOpenMessenger();
    }

    public synchronized boolean socketNull() {
        return socket == null;
    }

}






















