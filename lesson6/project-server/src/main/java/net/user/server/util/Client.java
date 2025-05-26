package net.user.server.util;

public class Client {

    private final String ipAddress;
    private final int port;
    private String nickname;
    private String password;

    public Client(String ipAddress, int port, String nickname, String password) {
        this.ipAddress = ipAddress;
        this.port = port;
        this.nickname = nickname;
        this.password = password;
    }

    public String getIpAddress() { return ipAddress; }
    public int getPort() { return port; }
    public String getNickname() { return nickname; }
    public String getPassword() { return password; }

    public void setNickname(String nickname) { this.nickname = nickname; }
    public void setPassword(String password) { this.password = password; }

}
