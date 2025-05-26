package net.user.client.ui.messenger;

import com.google.gson.Gson;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import lombok.Setter;
import net.user.client.data.ChatMessage;
import net.user.client.data.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class MessengerUI {

    @FXML
    public ListView<User> listView;
    @FXML
    public TextField messageArea;
    @FXML
    public Button sendBtn;
    public ChatArea chatArea;

    @Setter
    private String nickname;

    @FXML
    public void initialize() {
        listView.setCellFactory(info -> new UserCell());
        listView.getItems().addAll(
                new User("Serhii", "@serhii"),
                new User("Andrii", "@andrii"),
                new User("Taras", "@taras"),
                new User("Shush", "@shush")
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

    public static String getInput(String prompt) {
        TextInputDialog dialog = new TextInputDialog();
        dialog.setTitle("Input");
        dialog.setHeaderText(null);
        dialog.setContentText(prompt);

        Optional<String> result = dialog.showAndWait();
        return result.orElse(null);
    }

    public void addUser(ActionEvent actionEvent) {
        String input = getInput("Enter username");
        if (input == null) return;

        // System.out.println(input);
    }

    public void renameUser(ActionEvent actionEvent) {
        String input = getInput("Enter username");
        if (input == null) return;

        // System.out.println(input);
    }

    public void removeUser(ActionEvent actionEvent) {
        String input = getInput("Enter username");
        if (input == null) return;

        // System.out.println(input);
    }
}
