package net.user.client.requests;

import com.google.gson.JsonObject;
import net.user.client.Main;

public class LoginData extends AbstractData {

    String nickname;
    String password;

    public LoginData(String nickname, String password) {
        super(0);   // 0 is the code for login data
        this.nickname = nickname;
        this.password = password;
    }

    @Override
    public String toJson() {
        JsonObject data = new JsonObject();
        data.addProperty("code", getCode());
        data.addProperty("nickname", nickname);
        data.addProperty("password", password);
        return Main.GSON.toJson(data);
    }

    public String getNickname() {
        return nickname;
    }

    public String getPassword() {
        return password;
    }
}
