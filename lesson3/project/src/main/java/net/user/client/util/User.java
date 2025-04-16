package net.user.client.util;

import net.user.client.ui.MessageBubble;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private List<MessageBubble> messages;
    private boolean isOnline;

    public User(String name) {
        this.name = name;
        this.messages = new ArrayList<>();
        this.isOnline = false;
    }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public List<MessageBubble> getMessages() { return messages; }
    public void addMessage(MessageBubble bubble) { messages.add(bubble); }
    public void addMessage(ChatMessage msg) { messages.add(new MessageBubble(msg)); }

    public void setOnline(boolean isOnline) { this.isOnline = isOnline; }
    public boolean isOnline() { return this.isOnline; }
}
