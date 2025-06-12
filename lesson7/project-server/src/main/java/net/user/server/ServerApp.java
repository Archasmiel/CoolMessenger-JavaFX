package net.user.server;

import javafx.application.Application;
import javafx.stage.Stage;
import net.user.server.ui.ServerUI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class ServerApp extends Application {

    private ConfigurableApplicationContext context;
    private ServerSocketService socketService;

    @Override
    public void start(Stage primaryStage) {
        // Запуск Spring, контекст записуємо до змінної
        context = SpringApplication.run(AppConfig.class);

        // Витягуємо з контексту наш сервіс, якщо нема - створиться
        var stageService = context.getBean(StageService.class);
        socketService = context.getBean(ServerSocketService.class);

        // stageService єдиний і існує у контексті
        socketService.start("0.0.0.0", 8080);
        stageService.setStage(primaryStage);
        stageService.loadServer();
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