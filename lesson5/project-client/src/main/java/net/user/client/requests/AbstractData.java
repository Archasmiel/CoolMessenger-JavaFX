package net.user.client.requests;

public abstract class AbstractData {

    private final int code;

    public AbstractData(int code) {
        this.code = code;
    }

    public int getCode() {
        return code;
    }

    public abstract String toJson();

}
