package im.wity.core;

import lombok.Builder;

@Builder
public record AuthToken(String token, Long expirationMS ) {

    public Long expirationInSec(){
        return expirationMS / 1000;
    }
}
