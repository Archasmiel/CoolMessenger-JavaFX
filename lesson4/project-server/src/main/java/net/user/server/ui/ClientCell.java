package net.user.server.ui;

import javafx.scene.control.ListCell;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import net.user.server.util.Client;

public class ClientCell extends ListCell<Client> {

    private final VBox container;
    private final Text ipPortText;
    private final Text nicknameText;

    public ClientCell() {
        ipPortText = new Text();
        ipPortText.setFill(Color.WHITE);
        nicknameText = new Text();
        nicknameText.setFill(Color.WHITE);
        container = new VBox(5, ipPortText, nicknameText);
        this.setStyle("-fx-border-color: #444; -fx-border-width: 1 1 1 1;");
    }

    @Override
    protected void updateItem(Client client, boolean empty) {
        super.updateItem(client, empty);
        if (empty || client == null) {
            setGraphic(null);
        } else {
            ipPortText.setText(client.getIpAddress() + ":" + client.getPort());
            nicknameText.setText(client.getNickname());
            setGraphic(container);
        }
    }
}
