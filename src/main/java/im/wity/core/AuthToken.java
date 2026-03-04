package im.wity.core;

import lombok.Builder;

public record AuthToken(String token, Long expirationMS ) {

    public Long expirationInSec(){
        return expirationMS / 1000;
    }

    public static AuthToken from(String token, Long expirationMS){
        return new AuthToken(token, expirationMS);
    }
}
