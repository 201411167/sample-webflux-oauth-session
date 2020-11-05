package me.minjun.oauth.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.ReactiveMapSessionRepository;
import org.springframework.session.ReactiveSessionRepository;
import org.springframework.session.config.annotation.web.server.EnableSpringWebSession;

import java.util.concurrent.ConcurrentHashMap;

/**
 *  to use In-Memory configuration
 */

@Configuration
@EnableSpringWebSession
//@EnableRedisWebSession -> to use Redis configuration
public class SessionConfig {
    @Bean
    public ReactiveSessionRepository reactiveSessionRepository(){
        return new ReactiveMapSessionRepository(new ConcurrentHashMap<>());
    }
}
