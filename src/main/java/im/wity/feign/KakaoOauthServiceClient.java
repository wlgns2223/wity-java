package im.wity.feign;

import im.wity.config.FeignConfig;
import im.wity.dto.auth.KakaoTokenRequest;
import im.wity.dto.auth.KakaoTokenResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "kakao-oauth-api",url = "${oauth.kakao.base-url}",configuration = FeignConfig.class)
public interface KakaoOauthServiceClient {

    @PostMapping(value = "/token",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    KakaoTokenResponse requestToken(@RequestBody KakaoTokenRequest tokenRequest);

}
