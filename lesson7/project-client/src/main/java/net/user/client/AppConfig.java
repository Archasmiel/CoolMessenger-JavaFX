package net.user.client;

import com.google.gson.Gson;
import net.user.client.ui.login.LoginUI;
import net.user.client.ui.messenger.MessengerUI;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {

    @Bean
    public Gson gson() {
        return new Gson();
    }

    @Bean
    public StageService stageService(ApplicationContext context) {
        return new StageService(context);
    }

    @Bean
    public ClientSocketService socketService(Gson gson) {
        return new ClientSocketService(gson);
    }

    @Bean
    public LoginUI loginUI(
            ClientSocketService socketService,
            StageService stageService,
            Gson gson
    ) {
        return new LoginUI(socketService, stageService, gson);
    }

    @Bean
    public MessengerUI messengerUI(

    ) {
        return new MessengerUI();
    }
}
