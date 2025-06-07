package net.user.server.request;

import com.google.gson.JsonObject;
import lombok.Getter;

@Getter
public class LoginData extends AbstractData {

    String nickname;
    String password;

    public LoginData(String nickname, String password) {
        super(0);   // 0 - code for login data
        this.nickname = nickname;
        this.password = password;
    }

    @Override
    public JsonObject toJson() {
        JsonObject data = super.toJson();
        data.addProperty("nickname", nickname);
        data.addProperty("password", password);
        return data;
    }
}
