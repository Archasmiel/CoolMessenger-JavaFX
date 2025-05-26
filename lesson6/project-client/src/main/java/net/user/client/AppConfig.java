package net.user.client;

import com.google.gson.Gson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan("net.user.client")
public class AppConfig {

    @Bean
    public Gson gson() {
        return new Gson();
    }

}
