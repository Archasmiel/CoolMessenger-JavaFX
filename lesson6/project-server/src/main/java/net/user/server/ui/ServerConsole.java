package net.user.server.ui;

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

    public void addMessage(MessageBubble bubble) {
        container.getChildren().add(bubble);   // додати в список повідомлення
        this.layout();   // змусити оновитися панельку
        this.setVvalue(1.0);   // прокрутити в самий низ
    }

}
