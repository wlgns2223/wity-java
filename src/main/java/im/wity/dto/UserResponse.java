package im.wity.dto;

import im.wity.constant.AuthProvider;
import im.wity.entity.User;

import java.time.LocalDateTime;

public record UserResponse(
        Long id,
        String email,
        AuthProvider authProvider,
        Boolean isOauth,
        String defaultPageName,
        Boolean isDeleted,
        String userName,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {

    public static UserResponse from(User user){
        return new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getAuthProvider(),
                user.getIsOauth(),
                user.getDefaultPageName().getPageName(),
                user.getIsDeleted(),
                user.getUserName(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }
}
