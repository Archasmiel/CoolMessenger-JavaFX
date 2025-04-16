package net.user.client;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.IOException;

public class MessengerUI {

    public static final String MAIN_UI = "/messenger.fxml";

    @FXML public ListView<User> listView;
    private final ObservableList<User> userList =
            FXCollections.observableArrayList();

    @FXML public TextArea textArea;
    @FXML public TextField messageArea;
    @FXML public Button sendBtn;

    @FXML
    public void initialize() {
        userList.addAll(
                new User("Serhii"),
                new User("Andrii"),
                new User("Taras"),
                new User("Shush")
        );
        listView.setItems(userList);
        listView.setCellFactory(info -> new UserCell());
    }

    public void show(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(MAIN_UI));
        Parent root = null;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // Scene and stage setup
        Scene scene = new Scene(root, 600, 400);
        stage.setTitle("Cool Messenger");
        stage.setScene(scene);
        stage.setMinWidth(400);
        stage.setMinHeight(300);
        stage.show();
    }

    public void sendMessage(ActionEvent actionEvent) {
        User user = listView.getSelectionModel().getSelectedItem();
        if (user != null) {
            user.addMessage("[" + user.getName() + "] " + messageArea.getText());
            textArea.setText(user.getMessages());
            messageArea.setText("");
        }
    }

    public void selectUser(MouseEvent mouseEvent) {
        User selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            textArea.setText(selected.getMessages());
        }
    }

    public static class User {
        private String name;
        private String messages;
        private boolean isOnline;

        public User(String name) {
            this.name = name;
            this.messages = "";
            this.isOnline = false;
        }

        public String getName() { return name; }
        public void setName(String name) { this.name = name; }

        public String getMessages() { return messages; }

        public void addMessage(String text) { messages += text + '\n'; }

        public void setOnline(boolean isOnline) { this.isOnline = isOnline; }
        public boolean isOnline() { return this.isOnline; }
    }

    public static class UserCell extends ListCell<User> {
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

}
