package net.user.client;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;
import net.user.client.ui.messenger.MessengerUI;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Setter
@Service
public class StageService {

    private Stage stage;

    public void loadLoginUI() {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/login.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Login");
        stage.setScene(scene);
        stage.setMinWidth(400);
        stage.setMinHeight(300);
        stage.show();
    }

    public void loadMessengerUI(String nickname) {
        FXMLLoader loader = new FXMLLoader(
                getClass().getResource("/login.fxml"));
        Parent root;
        try {
            root = loader.load();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        MessengerUI controller = loader.getController();
        controller.setNickname(nickname);

        Scene scene = new Scene(root, 400, 300);
        stage.setTitle("Messenger @" + nickname);
        stage.setScene(scene);
        stage.setMinWidth(400);
        stage.setMinHeight(300);
        stage.show();
    }

}
