package net.user.client.ui.messenger;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;
import net.user.client.data.ChatMessage;

public class MessageBubble extends VBox {

    public MessageBubble(ChatMessage msg) {
        // Відправник
        Text senderText = new Text(msg.getSender() + "\n");
        senderText.setStyle(
                "-fx-fill: black;" +
                "-fx-font-size: 12;" +
                "-fx-font-weight: bold;"
        );

        // Відступ від відправника
        Text spacer = new Text("\n");
        spacer.setStyle("-fx-font-size: 4;");

        // Повідомлення
        Text messageText = new Text(msg.getMessage());
        messageText.setStyle(
                "-fx-fill: " + (msg.isMe() ? "white" : "black") + ";" +
                "-fx-font-size: 14;"
        );

        // Зшивач текстів
        TextFlow flow = new TextFlow(senderText, spacer, messageText);
        flow.setMaxWidth(300);   // Для того щоб повідомлення переносилися на нові рядки
        flow.setTextAlignment(msg.isMe() ? // вирівнювання зліва/справа, як в telegram
                TextAlignment.RIGHT : TextAlignment.LEFT);
        flow.setStyle(
                "-fx-padding: 10;" +
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-width: 1.5;" +
                "-fx-background-color: " + (msg.isMe() ? "#4A90E2" : "#F0F0F0") + ";" +
                "-fx-border-color: " + (msg.isMe() ? "#276FBF" : "#CCCCCC") + ";"
        );

        // Контейнер - додаємо HBox для вирівнювання
        HBox bubbleWrapper = new HBox(flow);
        bubbleWrapper.setAlignment(msg.isMe() ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        bubbleWrapper.setPadding(new Insets(5));

        // Вертикально позиціонуємо
        // зверху - ім'я, знизу повідомлення (окремо)
        this.setAlignment(msg.isMe() ? Pos.CENTER_RIGHT : Pos.CENTER_LEFT);
        this.setPadding(new Insets(2));
        this.getChildren().add(bubbleWrapper);
    }
}
