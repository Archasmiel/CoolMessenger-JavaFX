package net.user.client.data;

public class ChatMessage {

    private final String sender;
    private final String message;
    private final boolean isMe;

    public ChatMessage(String sender, String message, boolean isMe) {
        this.sender = sender;
        this.message = message;
        this.isMe = isMe;
    }

    public ChatMessage(String sender, String message) {
        this(sender, message, false);
    }

    public String getSender() { return sender; }
    public String getMessage() { return message; }
    public boolean isMe() { return isMe; }

}
