package net.user.client;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class ClientApp extends Application {

    private ConfigurableApplicationContext context;

    @Override
    public void init() {
        context = SpringApplication.run(AppConfig.class);
    }

    @Override
    public void start(Stage primaryStage) {
        StageService stageService = context.getBean(StageService.class);
        stageService.setStage(primaryStage);
        stageService.loadLoginUI();
    }

    @Override
    public void stop() {
        context.close();
    }

    public static void main(String[] args) {
        launch(args);
    }
}