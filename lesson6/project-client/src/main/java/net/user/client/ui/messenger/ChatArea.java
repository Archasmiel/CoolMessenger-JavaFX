package net.user.client.ui.messenger;

import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;
import net.user.client.data.ChatMessage;
import net.user.client.data.User;

public class ChatArea extends ScrollPane {

    private final VBox container;

    public ChatArea() {
        container = new VBox(5);   // 5 - піксельні відступи між елементами
        container.setPadding(new Insets(10));   // внутрішні відступи (всі по 10 px)
        this.setContent(container);   // встановити основною панелькою VBox
        this.setFitToWidth(true);   // розтягувати на всю ширину
        this.setVbarPolicy(ScrollBarPolicy.ALWAYS);   // завжди є смуга прокручення
    }

    public void addMessage(MessageBubble bubble) {
        container.getChildren().add(bubble);   // додати в список повідомлення
        this.layout();   // змусити оновитися панельку
        this.setVvalue(1.0);   // прокрутити в самий низ
    }

    public MessageBubble genMessage(ChatMessage msg) {
        MessageBubble bubble = new MessageBubble(msg);
        bubble.maxWidthProperty().bind(   // зменшення ширини через смугу прокручення
                container.widthProperty().subtract(20)
        );
        return bubble;
    }

    public void loadAllMessages(User user) {
        container.getChildren().clear();
        for (MessageBubble bubble: user.getMessages()) {
            bubble.maxWidthProperty().bind(
                    container.widthProperty().subtract(20)
            );
            container.getChildren().add(bubble);
        }
        this.layout();
        this.setVvalue(1.0);
    }

}
