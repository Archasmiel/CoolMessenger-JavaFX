package net.user.server.request;

import com.google.gson.JsonObject;
import lombok.Getter;

@Getter
public class RegisterData extends AbstractData {

    String nickname;
    String password;

    public RegisterData(String nickname, String password) {
        super(3);
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
