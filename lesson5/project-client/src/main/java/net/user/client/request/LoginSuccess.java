package net.user.client.auth;

import com.google.gson.JsonObject;
import net.user.client.Main;
import net.user.client.data.AbstractData;

public class LoginSuccess extends AbstractData {

    public LoginSuccess() {
        super(1);  // 1 is the code for login success
    }

    @Override
    public String toJson() {
        JsonObject data = new JsonObject();
        data.addProperty("code", getCode());
        return Main.GSON.toJson(data);
    }
}
