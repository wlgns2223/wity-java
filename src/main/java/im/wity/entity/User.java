package im.wity.entity;

import im.wity.constant.AuthProvider;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
@NoArgsConstructor
public class User {
    @Id
    private Long id;

    @Column(nullable = false)
    String email;

    @Column
    String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    AuthProvider authProvider;

    @Column(nullable = false,name = "is_Oauth")
     Boolean isOauth;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public User(String email,String password, AuthProvider authProvider){
        this.email = email;
        this.password = password;
        this.authProvider = authProvider;
        this.isOauth = authProvider.isOauth();
    }

    public static User createLocalUser(String email, String password, AuthProvider authProvider){
        return User.builder().email(email).password(password).authProvider(authProvider).build();
    }





}
