package im.wity.controller;

import im.wity.dto.LocalSignUpRequest;
import im.wity.entity.User;
import im.wity.service.AuthService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id){
        authService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
