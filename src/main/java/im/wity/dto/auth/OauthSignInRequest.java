package im.wity.dto.auth;

import im.wity.vo.PageName;

public record OauthSignInRequest(String code, PageName defaultPageName) {
    public static OauthSignInRequest from(String code,String defaultPageName){
        return new OauthSignInRequest(code,PageName.of(defaultPageName));
    }
}

