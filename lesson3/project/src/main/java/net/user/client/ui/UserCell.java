package net.user.client.ui;

import javafx.geometry.Pos;
import javafx.scene.control.ListCell;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import net.user.client.util.User;

public class UserCell extends ListCell<User> {

    private final HBox content;
    private final Circle statusDot;
    private final Text nameText;

    public UserCell() {
        statusDot = new Circle(6);
        nameText = new Text();
        nameText.setFill(Color.WHITE);
        content = new HBox(10, statusDot, nameText);
        content.setAlignment(Pos.CENTER_LEFT);
    }

    @Override
    protected void updateItem(User user, boolean empty) {
        super.updateItem(user, empty);
        if (empty || user == null) {
            setGraphic(null);
        } else {
            nameText.setText(user.getName());
            statusDot.setFill(user.isOnline() ? Color.LIMEGREEN : Color.GRAY);
            setGraphic(content);
        }
    }
}