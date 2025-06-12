package net.user.server.ui;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.scene.text.TextFlow;

public class MessageBubble extends VBox {

    private final TextFlow headerFlow;
    private final TextFlow messageFlow;

    public MessageBubble(String type, String msg) {
        // Заголовок і повідомлення як окремі TextFlow
        headerFlow = createHeader(type);
        messageFlow = createMessage(msg);

        // Оформлення бульбашки – застосовується до wrapper
        this.setStyle(
                "-fx-background-radius: 12;" +
                "-fx-border-radius: 12;" +
                "-fx-border-width: 1.5;" +
                "-fx-background-color: #4A90E2;" +
                "-fx-border-color: #276FBF;"
        );
        this.setPadding(new Insets(10));   // внутрішні відступи 10px
        this.setSpacing(4); // відстань між вкладеними об'єктами в бульбашку

        this.setAlignment(Pos.CENTER_LEFT);
        this.getChildren().addAll(headerFlow, messageFlow);
    }

    private TextFlow createHeader(String type) {
        Text typeText = new Text(type);
        typeText.setStyle(
                "-fx-fill: black;" +
                "-fx-font-size: 12;" +
                "-fx-font-weight: bold;"
        );
        return new TextFlow(typeText);
    }

    private TextFlow createMessage(String msg) {
        Text messageText = new Text(msg);
        messageText.setStyle(
                "-fx-fill: white; " +
                "-fx-font-size: 14;"
        );
        return new TextFlow(messageText);
    }

    public String getHeaderText() {
        if (headerFlow.getChildren().getFirst() instanceof Text text) {
            return text.getText();
        }
        return null;
    }

    public String getMessageText() {
        if (messageFlow.getChildren().getFirst() instanceof Text text) {
            return text.getText();
        }
        return null;
    }

    public void setHeaderText(String str) {
        if (headerFlow.getChildren().getFirst() instanceof Text text) {
            text.setText(str);
        }
    }

    public void setMessageText(String str) {
        if (messageFlow.getChildren().getFirst() instanceof Text text) {
            text.setText(str);
        }
    }
}
