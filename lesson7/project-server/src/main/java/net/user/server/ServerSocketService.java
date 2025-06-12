package net.user.server;

import com.google.gson.Gson;
import javafx.collections.ObservableList;
import net.user.server.data.Client;
import net.user.server.ui.ServerConsole;
import net.user.server.websocket.ServerWebSocket;
import org.java_websocket.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ServerSocketService {

    private final Gson gson;
    private final ObservableList<Client> clients;
    private final ServerConsole serverConsole;
    private final AuthService authService;
    private ServerWebSocket socket;

    @Autowired
    public ServerSocketService(
            Gson gson,
            ObservableList<Client> clients,
            ServerConsole serverConsole,
            AuthService authService
    ) {
        this.gson = gson;
        this.clients = clients;
        this.serverConsole = serverConsole;
        this.authService = authService;
    }

    public synchronized void start(
            String ip, int port
    ) {
        socket = new ServerWebSocket(ip, port);
        socket.setGson(gson);
        socket.setClientList(clients);
        socket.setConsole(serverConsole);
        socket.setAuthService(authService);
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
