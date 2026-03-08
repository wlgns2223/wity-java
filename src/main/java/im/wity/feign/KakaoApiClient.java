package im.wity.feign;

import im.wity.dto.auth.KakaoOauthResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name = "kakao-api-client",url = "https://kapi.kakao.com/v2/")
public interface KakaoApiClient {

    @GetMapping(value = "/user/me",consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    KakaoOauthResponse requestUser(@RequestHeader("Authorization") String token);
}
