package me.minjun.oauth.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.minjun.oauth.domain.oauth.OAuth2UserInfo;
import me.minjun.oauth.domain.oauth.OAuth2UserInfoFactory;
import me.minjun.oauth.domain.oauth.OAuth2UserInfoRepository;
import me.minjun.oauth.domain.user.UserRepository;
import org.springframework.security.oauth2.client.userinfo.DefaultReactiveOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.client.userinfo.ReactiveOAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Map;

@Slf4j
@Service
@RequiredArgsConstructor
public class OAuthService implements ReactiveOAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;
    private final OAuth2UserInfoRepository oAuth2UserInfoRepository;

    @Override
    public Mono<OAuth2User> loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        final DefaultReactiveOAuth2UserService delegate = new DefaultReactiveOAuth2UserService();
        final String clientRegistrationId = userRequest.getClientRegistration().getRegistrationId();

        Mono<OAuth2User> oAuth2User = delegate.loadUser(userRequest);

        return oAuth2User.flatMap(e -> {
            OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(clientRegistrationId, e.getAttributes());

            log.info("OAuthService -> laodUser : " + e.getAttributes().toString());

            return oAuth2UserInfoRepository
                    .findById(oAuth2UserInfo.getEmail())
                    .switchIfEmpty(Mono.defer(() -> oAuth2UserInfoRepository.save(oAuth2UserInfo)));
        });
    }
}

