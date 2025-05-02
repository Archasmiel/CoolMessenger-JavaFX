package net.user.client;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.function.Consumer;
import java.util.function.Function;

public class Main extends Application {

    private static Stage PRIMARY_STAGE;

    @Override
    public void start(Stage primaryStage) {
        PRIMARY_STAGE = primaryStage;
        loadUI(stage -> new LoginUI().show(stage));

    }

    public static void loadUI(Consumer<Stage> stageLoader) {
        stageLoader.accept(PRIMARY_STAGE);
    }

    public static void main(String[] args) {
        launch(args);
    }
}