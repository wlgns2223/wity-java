package im.wity.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "oauth.kakao")
public record KakaoOauthProperties(String clientId, String redirectUri, String tokenUrl ) {
}
