package im.wity.entity;

import im.wity.constant.AuthProvider;
import im.wity.entity.vo.Password;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
@NoArgsConstructor(access =AccessLevel.PROTECTED)
public class User {
    @Id
    private Long id;

    @Column(nullable = false)
    private String email;

    @Embedded
    private Password password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider authProvider;

    @Column(nullable = false,name = "is_Oauth")
    private Boolean isOauth;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public User(String email,Password password, AuthProvider authProvider){
        this.email = email;
        this.password = password;
        this.authProvider = authProvider;
        this.isOauth = authProvider.isOauth();
    }

    public static User createLocalUser(String email, String password, AuthProvider authProvider){
        return User.builder().email(email).password(Password.Of(password)).authProvider(authProvider).build();
    }
}
