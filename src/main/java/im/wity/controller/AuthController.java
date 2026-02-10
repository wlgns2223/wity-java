package im.wity.controller;

import im.wity.dto.LocalSignUpRequest;
import im.wity.entity.User;
import im.wity.service.AuthService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/local-signup")
    ResponseEntity<User> signUp(
            @RequestBody
            @NotNull
            LocalSignUpRequest localSignUpRequest) {

        return ResponseEntity.ok(authService.signUp(localSignUpRequest));
    }
}
