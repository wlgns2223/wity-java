package im.wity.controller;

import im.wity.dto.LocalSignUpRequest;
import im.wity.entity.User;
import im.wity.service.AuthService;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

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

        User user = authService.signUp(localSignUpRequest);
        URI location = ServletUriComponentsBuilder
                .fromPath("/api/user/{id}")
                .buildAndExpand(user.getId())
                .toUri();

        return ResponseEntity.created(location).body(user);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<Void> delete(@PathVariable Long id){
        authService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
