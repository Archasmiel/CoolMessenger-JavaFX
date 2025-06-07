package net.user.server;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class StageService {

    private final ApplicationContext context;
    @Setter private Stage stage;

    @Autowired
    public StageService(ApplicationContext context) {
        this.context = context;
    }

    public void loadServer() {
        loadFXML("/server.fxml", "Server");
    }

    private void loadFXML(
            String fxmlPath,
            String title
    ) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource(fxmlPath));

            // Важливо для Spring, при створенні буде використано
            // class який відповідає нашому .fxml controller
            // Обов'язкова анотація класу @Component/@Service
            loader.setControllerFactory(context::getBean);

            Parent root = loader.load();
            Scene scene = new Scene(root, 400, 300);
            stage.setTitle(title);
            stage.setScene(scene);
            stage.setMinWidth(400);
            stage.setMinHeight(300);
            stage.show();
        } catch (IOException e) {
            throw new RuntimeException("Failed to load FXML: " + fxmlPath, e);
        }
    }

}
