package net.user.server;

import javafx.application.Application;
import javafx.stage.Stage;
import net.user.server.ui.ServerUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApp extends Application {

    @Override
    public void start(Stage primaryStage) {
        // Запуск Spring, контекст записуємо до змінної
        var context = SpringApplication.run(AppConfig.class);

        // Витягуємо з контексту наш сервіс, якщо нема - створиться
        var stageService = context.getBean(StageService.class);

        // stageService єдиний і існує у контексті
        stageService.setStage(primaryStage);
        stageService.loadServer();
    }

    public static void main(String[] args) {
        // Запуск JavaFX
        launch(args);
    }
}