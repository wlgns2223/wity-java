package im.wity.dto.auth;

import feign.form.FormProperty;
import im.wity.config.KakaoOauthProperties;
import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class KakaoTokenRequest {

    @FormProperty("grant_type")
    private String grantType;

    @FormProperty("client_id")
    private String clientId;

    @FormProperty("redirect_uri")
    private String redirectUri;

    @FormProperty("code")
    private String code;


    public static KakaoTokenRequest from(KakaoOauthProperties properties, String code){
        return KakaoTokenRequest.builder()
                .code(code)
                .clientId(properties.clientId())
                .grantType("authorization_code")
                .redirectUri(properties.redirectUri())
                .build();
    }

}
