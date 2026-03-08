package im.wity.controller;

import im.wity.core.AuthResult;
import im.wity.dto.auth.LocalSignInRequest;
import im.wity.dto.auth.LocalSignUpRequest;
import im.wity.dto.auth.OauthSignInRequest;
import im.wity.dto.auth.SignInResponse;
import im.wity.dto.user.UserResponse;
import im.wity.entity.User;
import im.wity.service.AuthService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseCookie;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.Duration;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {


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
    ResponseEntity<UserResponse> localSignIn(@Valid @RequestBody LocalSignInRequest signInRequest){

        SignInResponse signInResponse = authService.signIn(signInRequest);

        ResponseEntity.BodyBuilder builder = ResponseEntity.ok();

        signInResponse.authResult().cookies().forEach(cookie -> {
            builder.header(HttpHeaders.SET_COOKIE,
                    ResponseCookie.from(cookie.name().getValue(), cookie.value())
                            .httpOnly(true)
                            .secure(false)
                            .path("/")
                            .maxAge(cookie.expirationSeconds())
                            .sameSite("lax")
                            .toString()
            );
        });

        return builder.body(signInResponse.userResponse());
    }

    @GetMapping("/kakao/callback")
    void kakaoCallback(@RequestParam String code,@RequestParam String defaultPageName){
        SignInResponse signInResponse = authService.oauthSignIn(OauthSignInRequest.from(code,defaultPageName));

    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id){
        authService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
