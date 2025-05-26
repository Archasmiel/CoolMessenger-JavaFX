package net.user.server;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;
import net.user.server.ui.ClientCell;
import net.user.server.ui.MessageBubble;
import net.user.server.ui.ServerConsole;
import net.user.server.util.Client;

public class ServerUI {

    public static final String MAIN_UI = "/server.fxml";

    @FXML public TextField commandField;
    @FXML public Button sendButton;
    @FXML public ListView<Client> clientList;
    public ServerConsole console;
    @FXML public BorderPane root;

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
        stage.setTitle("Cool Server");
        stage.setScene(scene);
        stage.setMinWidth(400);
        stage.setMinHeight(300);
        stage.show();
    }

    @FXML
    public void initialize() {
        clientList.setSelectionModel(null);  // не можна обирати клієнта
        clientList.setCellFactory(info -> new ClientCell());   // завод ячейок для клієнтів
        clientList.getItems().addAll(
            new Client("0.0.0.0", 8080, "@serhii", "password"),
            new Client("0.0.0.0", 8081, "@andrii", "password"),
            new Client("0.0.0.0", 8081, "@andrii", "password"),
            new Client("0.0.0.0", 8081, "@andrii", "password"),
            new Client("0.0.0.0", 8081, "@andrii", "password"),
            new Client("0.0.0.0", 8081, "@andrii", "password"),
            new Client("0.0.0.0", 8081, "@andrii", "password"),
            new Client("0.0.0.0", 8081, "@andrii", "password"),
            new Client("0.0.0.0", 8082, "@taras", "password")
        );

        console = new ServerConsole();
        root.setCenter(console);
        console.addMessage(new MessageBubble("TEST", "Nagger"));
        console.addMessage(new MessageBubble("TEST", "Lorem ipsum"));
        console.addMessage(new MessageBubble("TEST", "Lorem ipsum dolor sit " +
                "amet, consectetur adipiscing elit."));
    }

}
