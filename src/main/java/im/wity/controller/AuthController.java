package im.wity.controller;

import im.wity.dto.LocalSignUpRequest;
import im.wity.entity.User;
import im.wity.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;


    @PostMapping
    ResponseEntity<User> signUp(LocalSignUpRequest localSignUpRequest) throws InterruptedException {

        return ResponseEntity.ok(authService.signUp(localSignUpRequest));
    }
}
