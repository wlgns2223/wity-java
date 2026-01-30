package im.wity.controller;

import im.wity.dto.SignUpRequestDto;
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
    ResponseEntity<String> signUp(SignUpRequestDto signUpRequestDto){
        authService.signUp(signUpRequestDto);
        return ResponseEntity.ok("");
    }
}
