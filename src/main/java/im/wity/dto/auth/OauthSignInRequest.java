package im.wity.dto.auth;

public record OauthSignInRequest(String code) {
    public static OauthSignInRequest from(String code){
        return new OauthSignInRequest(code);
    }
}

