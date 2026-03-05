package im.wity.components;

import im.wity.config.KakaoOauthProperties;
import im.wity.dto.auth.KakaoTokenRequest;
import im.wity.dto.auth.KakaoTokenResponse;
import im.wity.entity.User;
import im.wity.feign.KakaoOauthServiceClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthManager {

    private final KakaoOauthProperties kakaoOauthProperties;
    private final KakaoOauthServiceClient kakaoOauthServiceClient;

    public User processOauth(String code){
        KakaoTokenResponse response = kakaoOauthServiceClient.requestToken(KakaoTokenRequest.from(kakaoOauthProperties, code));
        System.out.println("response " + response.toString());
        return null;
    }
}
