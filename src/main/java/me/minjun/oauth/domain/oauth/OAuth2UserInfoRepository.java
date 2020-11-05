package me.minjun.oauth.domain.oauth;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;

public interface OAuth2UserInfoRepository extends ReactiveMongoRepository<OAuth2UserInfo, String> {
}
