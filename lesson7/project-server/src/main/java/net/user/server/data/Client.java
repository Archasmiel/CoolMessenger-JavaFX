package net.user.server.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.java_websocket.WebSocket;

@AllArgsConstructor
@Getter
public class Client {

    private final String ipAddress;
    private final int port;
    private final WebSocket webSocket;

    @Setter
    private String nickname;
    @Setter
    private String password;

}
