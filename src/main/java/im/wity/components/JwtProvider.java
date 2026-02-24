package im.wity.components;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.io.Encoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Arrays;
import java.util.Date;

@Component
public class JwtProvider {
    private final SecretKey secretKey;
    private final long accessExpirationMS;
    private final long refreshExpirationMS;

    public JwtProvider(
            @Value("${jwt.secret}") String secret,
            @Value("${jwt.access-expiration-ms}") long accessExpirationMS,
            @Value("${jwt.refresh-expiration-ms}") long refreshExpirationMS
    ) {
        this.secretKey = Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
        this.accessExpirationMS = accessExpirationMS;
        this.refreshExpirationMS = refreshExpirationMS;
    }

    public String createAccessToken(String email){
        return buildToken(email, accessExpirationMS, "access");
    }

    public String createRefreshToken(String email){
        return buildToken(email, refreshExpirationMS, "refresh");
    }

    private String buildToken(String email, long expirationMS, String tokenType){
        Date now = new Date();

        return Jwts.builder().subject(email)
                .claim("type", tokenType)
                .issuedAt(now)
                .expiration(new Date(now.getTime() + expirationMS))
                .signWith(secretKey,Jwts.SIG.HS256)
                .compact();
    }

    public Claims parseToken(String token){
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public long getAccessExpirationSeconds(){
        return accessExpirationMS / 1000;
    }

}
