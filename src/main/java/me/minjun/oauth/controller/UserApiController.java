package me.minjun.oauth.controller;

import lombok.RequiredArgsConstructor;
import me.minjun.oauth.domain.oauth.CurrentUser;
import me.minjun.oauth.domain.oauth.OAuth2UserInfo;
import me.minjun.oauth.domain.oauth.OAuth2UserInfoRepository;
import me.minjun.oauth.domain.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.util.Map;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserApiController {

    @Autowired
    OAuth2UserInfoRepository oAuth2UserInfoRepository;

    @GetMapping("/hello")
    public Mono<String> hello(){
        return Mono.just("hello world");
    }

    @GetMapping("/oauth")
    public Mono<String> oauth2Login(WebSession webSession){
        webSession.getAttributes().putIfAbsent("note", "howdy cosmic spheroid!");


        return Mono.just("Authentication Finished");
    }

    @GetMapping("/session")
    public Mono<String> currentSessionId(@CurrentUser  OAuth2UserInfo oAuth2UserInfo){
        String email = oAuth2UserInfo.getEmail();
        return Mono.just(email);
    }
}
