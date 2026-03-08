package im.wity.components;

import im.wity.config.KakaoOauthProperties;
import im.wity.dto.auth.KakaoOauthResponse;
import im.wity.dto.auth.KakaoTokenRequest;
import im.wity.dto.auth.KakaoTokenResponse;
import im.wity.entity.User;
import im.wity.feign.KakaoApiClient;
import im.wity.feign.KakaoOauthServiceClient;
import im.wity.repository.UserRepository;
import im.wity.vo.PageName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OAuthManager {

    private final KakaoOauthProperties kakaoOauthProperties;
    private final KakaoOauthServiceClient kakaoOauthServiceClient;
    private final KakaoApiClient kakaoApiClient;
    private final UserRepository userRepository;

    public User processOauth(String code, PageName defaultPageName){
        KakaoTokenResponse response = kakaoOauthServiceClient.requestToken(KakaoTokenRequest.from(kakaoOauthProperties, code));
        KakaoOauthResponse oauthResponse = kakaoApiClient.requestUser("Bearer " + response.getAccessToken());

        return userRepository.findByEmail(oauthResponse.getKakaoAccount().getEmail()).orElseGet(() -> {
            User created = User.createOauthUser(oauthResponse.getKakaoAccount().getEmail(),
                    defaultPageName,
                    oauthResponse.getKakaoAccount().getProfile().getNickname()
                    );
            return userRepository.save(created);
        });
    }
}
