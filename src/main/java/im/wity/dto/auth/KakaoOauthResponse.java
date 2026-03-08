package im.wity.dto.auth;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@ToString
public class KakaoOauthResponse {

    @JsonProperty("kakao_account")
    KakaoAccount kakaoAccount;

    @ToString
    @Getter
    @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
    public static class KakaoAccount{
        private String name;
        private String email;
        private Profile profile;

        @ToString
        @Getter
        @JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
        public static class Profile {
            private String nickname;
        }
    }
}
