package me.minjun.oauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;

@SpringBootApplication
public class OauthApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(OauthApplication.class);
        app.addListeners((ApplicationStartedEvent event) -> {
            System.out.println("+++++ Oauth Started +++++");
        });
        app.run(args);
    }

}
