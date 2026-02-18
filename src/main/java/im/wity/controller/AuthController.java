package im.wity.controller;

import im.wity.dto.LocalSignInRequest;
import im.wity.dto.LocalSignInResponse;
import im.wity.dto.LocalSignUpRequest;
import im.wity.entity.User;
import im.wity.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    @Value("${jwt.access-expiration-ms}")
    private long accessExpirationMS;

    @Value("${jwt.refresh-expiration-ms}")
    private long refreshExpirationMS;


    private final AuthService authService;

    @PostMapping("/local-sign-up")
    ResponseEntity<User> signUp(
            @RequestBody
            @NotNull
            LocalSignUpRequest localSignUpRequest) {

        authService.signUp(localSignUpRequest);

        return ResponseEntity.ok().build();
    }

    @PostMapping("/local-sign-in")
    ResponseEntity<User> localSignIn(@Valid @RequestBody LocalSignInRequest signInRequest){

        LocalSignInResponse signInResponse = authService.signIn(signInRequest);

        ResponseCookie accessCookie = ResponseCookie.from("access_token", signInResponse.accessToken())
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(Duration.ofMillis(accessExpirationMS))
                .sameSite("lax")
                .build();

        ResponseCookie refreshCookie = ResponseCookie.from("refresh_token", signInResponse.refreshToken())
                .httpOnly(true)
                .secure(false)
                .path("/")
                .maxAge(Duration.ofMillis(refreshExpirationMS))
                .sameSite("lax")
                .build();


        return ResponseEntity.ok()
                .header(HttpHeaders.SET_COOKIE,accessCookie.toString())
                .header(HttpHeaders.SET_COOKIE,refreshCookie.toString())
                .body(signInResponse.user());

    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id){
        authService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
