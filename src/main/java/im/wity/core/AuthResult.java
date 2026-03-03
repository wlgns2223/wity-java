package im.wity.core;

import im.wity.dto.auth.AuthCookie;

import java.util.List;

public interface AuthResult {
    List<AuthCookie> cookies();

}
