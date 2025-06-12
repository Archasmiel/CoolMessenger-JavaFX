package net.user.client;

import javafx.application.Application;
import javafx.stage.Stage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ClientApp extends Application {

    private ConfigurableApplicationContext context;
    private ClientSocketService socketService;

    @Override
    public void start(Stage primaryStage) {
        // Запуск Spring, контекст записуємо до змінної
        context = SpringApplication.run(AppConfig.class);

        // Витягуємо з контексту наш сервіс, якщо нема - створиться
        var stageService = context.getBean(StageService.class);
        socketService = context.getBean(ClientSocketService.class);

        // stageService єдиний і існує у контексті
        stageService.setStage(primaryStage);
        stageService.loadLoginUI();
    }

    @Override
    public void stop() {
        socketService.close();
        System.exit(0);
    }

    public static void main(String[] args) {
        // Запуск JavaFX
        launch(args);
    }
}