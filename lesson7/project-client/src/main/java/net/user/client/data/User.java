package net.user.client.data;

import lombok.Getter;
import lombok.Setter;
import net.user.client.ui.messenger.MessageBubble;

import java.util.ArrayList;
import java.util.List;

@Getter
public class User {

    @Setter private String name;
    @Setter private String nickname;
    private final List<MessageBubble> messages;
    @Setter private boolean isOnline;

    public User(String name, String nickname) {
        this.name = name;
        this.nickname = nickname;
        this.messages = new ArrayList<>();
        this.isOnline = false;
    }

    public void addMessage(MessageBubble bubble) { messages.add(bubble); }
    public void addMessage(ChatMessage msg) { messages.add(new MessageBubble(msg)); }

}
