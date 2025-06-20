package net.user.server.ui;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

import net.user.server.ServerSocketService;
import net.user.server.StageService;
import net.user.server.data.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope("prototype")
public class ServerUI {

    private final ObservableList<Client> clients;
    private final ServerConsole console;

    @FXML public TextField commandField;
    @FXML public Button sendButton;
    @FXML public ListView<Client> clientList;
    @FXML public BorderPane root;

    @Autowired
    public ServerUI(
            ObservableList<Client> clients,
            ServerConsole console
    ) {
        this.clients = clients;
        this.console = console;
    }

    @FXML
    public void initialize() {
        clientList.setCellFactory(info -> new ClientCell());
        clientList.setSelectionModel(null);
        clientList.setItems(clients);

        root.setCenter(console);
    }

}
