package net.user.server.ui;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.VBox;

public class ServerConsole extends ScrollPane {

    private final VBox container;

    public ServerConsole() {
        container = new VBox(5);   // 5 - піксельні відступи між елементами
        container.setPadding(new Insets(10));   // внутрішні відступи (всі по 10 px)
        this.setContent(container);   // встановити основною панелькою VBox
        this.setFitToWidth(true);   // розтягувати на всю ширину
        this.setVbarPolicy(ScrollBarPolicy.ALWAYS);   // завжди є смуга прокручення
    }

    public void clearAll() {
        container.getChildren().clear();
    }

    public boolean remove(int index) {
        if (container.getChildren().size() <= index) {
            return false;
        }
        container.getChildren().remove(index);
        return true;
    }

    public MessageBubble get(int index) {
        if (container.getChildren().size() <= index) {
            return null;
        }
        return (MessageBubble) container.getChildren().get(index);
        // явне приведення до типу даних --- (type) variable
        // змінна має бути наслідуванням типу або простим типом
        // у нашому випадку --- MessageBubble -> VBox ... -> Node
    }

    public void addMessage(MessageBubble bubble) {
        container.getChildren().add(bubble);   // додати в список повідомлення
        this.layout();   // змусити оновитися панельку
        this.setVvalue(1.0);   // прокрутити в самий низ
    }

    public void addMessage(String header, String msg) {
        this.addMessage( new MessageBubble(header, msg) );
    }

}
