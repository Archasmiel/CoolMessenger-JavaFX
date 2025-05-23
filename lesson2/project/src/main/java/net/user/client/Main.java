package net.user.client;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        MessengerUI ui = new MessengerUI();
        ui.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}