package me.minjun.oauth.domain.oauth;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        return new OAuth2UserInfo(attributes);
    }
}
