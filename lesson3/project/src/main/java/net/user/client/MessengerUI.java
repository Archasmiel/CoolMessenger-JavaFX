package net.user.client;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import net.user.client.ui.ChatArea;
import net.user.client.ui.MessageBubble;
import net.user.client.ui.UserCell;
import net.user.client.util.ChatMessage;
import net.user.client.util.User;

import java.io.IOException;

public class MessengerUI {

    public static final String MAIN_UI = "/messenger.fxml";

    @FXML public ListView<User> listView;
    @FXML public TextField messageArea;
    @FXML public Button sendBtn;
    public ChatArea chatArea;

    public void show(Stage stage) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource(MAIN_UI));
        Parent root;
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

    @FXML
    public void initialize() {
        listView.setCellFactory(info -> new UserCell());
        listView.getItems().addAll(
                new User("Serhii"),
                new User("Andrii"),
                new User("Taras"),
                new User("Shush")
        );

        chatArea = new ChatArea();
        if (listView.getParent() instanceof BorderPane pane) {
            pane.setCenter(chatArea);
        }
    }

    public void sendMessage(ActionEvent actionEvent) {
        User user = listView.getSelectionModel().getSelectedItem();
        if (user != null) {
            ChatMessage msg = new ChatMessage("Me", messageArea.getText(), true);
            MessageBubble bubble = chatArea.genMessage(msg);
            user.addMessage(bubble);
            chatArea.addMessage(bubble);
            messageArea.setText("");
        }
    }

    public void selectUser(MouseEvent mouseEvent) {
        User selected = listView.getSelectionModel().getSelectedItem();
        if (selected != null) {
            chatArea.loadAllMessages(selected);
        }
    }

}
