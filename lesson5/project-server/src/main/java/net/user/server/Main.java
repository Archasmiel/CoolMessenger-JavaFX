package net.user.server;

import javafx.application.Application;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        ServerUI ui = new ServerUI();
        ui.show(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }
}