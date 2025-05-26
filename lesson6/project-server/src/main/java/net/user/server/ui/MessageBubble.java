package net.user.server.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class MessageBubble extends VBox {

    public MessageBubble(String type, String msg) {
        // Відправник
        Text typeText = new Text(type + "\n");
        typeText.setStyle(
                "-fx-fill: red;" +
                        "-fx-font-size: 12;" +
                        "-fx-font-weight: bold;");

        // Відступ від відправника
        Text spacer = new Text("\n");
        spacer.setStyle("-fx-font-size: 4;");

        // Повідомлення
        Text messageText = new Text(msg);
        messageText.setStyle(
                "-fx-fill: white; -fx-font-size: 14;"
        );

        // Зшивач текстів
        TextFlow flow = new TextFlow(typeText, spacer, messageText);
        flow.setMaxWidth(300);   // Для того щоб повідомлення переносилися на нові рядки
        flow.setTextAlignment(TextAlignment.LEFT);
        flow.setStyle(
                "-fx-padding: 10;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-width: 1.5;" +
                "-fx-background-color: #4A90E2;" +
                "-fx-border-color: #276FBF;"
        );

        // Вертикально позиціонуємо
        // зверху - ім'я, знизу повідомлення (окремо)
        this.setAlignment(Pos.CENTER_LEFT);
        this.setPadding(new Insets(2));
        this.getChildren().add(flow);
    }

}
