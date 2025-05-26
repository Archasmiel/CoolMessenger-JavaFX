package net.user.client.ui.messenger;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import net.user.client.data.User;

public class UserCell extends ListCell<User> {

    private final HBox content;
    private final Circle statusDot;
    private final Text nameText;
    private final Text nicknameText;

    public UserCell() {
        statusDot = new Circle(6);

        nameText = new Text();
        nameText.setFill(Color.WHITE);
        nicknameText = new Text();
        nicknameText.setFill(Color.GRAY);
        VBox textContainer =
                new VBox(5, nameText, nicknameText);

        content = new HBox(10, statusDot, textContainer);
        content.setAlignment(Pos.CENTER_LEFT);
    }

    @Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);
        if (empty || user == null) {
            setGraphic(null);
        } else {
            nameText.setText(user.getName());
            nicknameText.setText(user.getNickname());
            statusDot.setFill(user.isOnline() ? Color.LIMEGREEN : Color.GRAY);
            setGraphic(content);
        }
    }
}