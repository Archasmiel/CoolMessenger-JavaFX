package net.user.server;

import com.google.gson.Gson;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import net.user.server.data.Client;
import net.user.server.ui.ServerUI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public ObservableList<Client> clients() {
        return FXCollections.emptyObservableList();
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
    public ServerSocketService socketService(Gson gson) {
        return new ServerSocketService(gson);
    }

    @Bean
    public ServerUI serverUI(ObservableList<Client> clients) {
        return new ServerUI(clients);
    }

}
