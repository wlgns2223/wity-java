package im.wity.controller;

import im.wity.dto.user.UserResponse;
import im.wity.dto.user.UserUpdateRequest;
import im.wity.entity.User;
import im.wity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PutMapping("/{id}")
    ResponseEntity<UserResponse> updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest updateRequest){
        User user = userService.update(id, updateRequest);
        return ResponseEntity.ok(UserResponse.from(user));

    }
}
