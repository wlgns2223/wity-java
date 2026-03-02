package im.wity.entity;

import im.wity.constant.AuthProvider;
import im.wity.dto.user.UserUpdate;
import im.wity.vo.PageName;
import im.wity.vo.PageNameConverter;
import im.wity.vo.Password;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "user")
@NoArgsConstructor(access =AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder(access = AccessLevel.PRIVATE)
public class User extends BaseEntity {

    @Column(nullable = false,unique = true)
    private String email;

    @Embedded
    private Password password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AuthProvider authProvider;

    @Column(nullable = false,name = "is_Oauth")
    private Boolean isOauth;

    @Column
    @Convert(converter = PageNameConverter.class)
    private PageName defaultPageName;

    @Column
    private Boolean isDeleted;

    @Column
    private String userName;

    public static User createLocalUser(
            String email,
            Password encodedPassword,
            PageName defaultPageName,
            String userName
            ){

        AuthProvider provider = AuthProvider.LOCAL;
        return User.builder()
                .email(email)
                .password(encodedPassword)
                .authProvider(provider)
                .defaultPageName(defaultPageName)
                .userName(userName)
                .isOauth(provider.isOauth())
                .isDeleted(false)
                .build();
    }

    public void update(UserUpdate userUpdate){
        this.defaultPageName = userUpdate.pageName();
        this.userName = userUpdate.userName();

    }
}
