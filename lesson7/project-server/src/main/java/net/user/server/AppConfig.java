package net.user.server;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.user.server.data.Client;
import net.user.server.ui.ServerConsole;
import net.user.server.ui.ServerUI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ObservableList<Client> clients() {
        return FXCollections.observableArrayList();
    }

    @Bean
    public ServerConsole serverConsole() {
        return new ServerConsole();
    }

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public StageService stageService(ApplicationContext context) {
        return new StageService(context);
    }

    @Bean
    public ServerSocketService socketService(
            Gson gson,
            ObservableList<Client> clients,
            ServerConsole serverConsole,
            AuthService authService
    ) {
        return new ServerSocketService(gson, clients, serverConsole, authService);
    }

    @Bean
    public ServerUI serverUI(
            ObservableList<Client> clients,
            ServerConsole serverConsole
    ) {
        return new ServerUI(clients, serverConsole);
    }

    @Bean
    public AuthService authService(Gson gson) {
        return new AuthService(gson);
    }

}
