package net.user.client.request;

import com.google.gson.JsonObject;
import lombok.Getter;

@Getter
public abstract class AbstractData {

    private final int code;

    protected AbstractData(int code) {
        this.code = code;
    }

    public JsonObject toJson() {
        JsonObject data = new JsonObject();
        data.addProperty("code", getCode());
        return data;
    }

}
