package net.user.client.request;

import com.google.gson.JsonObject;
import net.user.client.Main;

public class LoginFailed extends AbstractData {

    public LoginFailed() {
        super(2);  // 1 is the code for login success
    }

    @Override
    public String toJson() {
        JsonObject data = new JsonObject();
        data.addProperty("code", getCode());
        return Main.GSON.toJson(data);
    }
}
