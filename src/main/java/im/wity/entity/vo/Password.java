package im.wity.entity.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    private static final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    
    @Column(name = "password")
    String encryptedPassword;

    private Password(String password){
        this.encryptedPassword = password;
    }

    public static Password Of(String plain){
        validate(plain);
        return new Password(encode(plain));
    }

    private static void validate(String plainPassword){
        if(plainPassword == null || plainPassword.isEmpty()){
            throw new IllegalArgumentException("비밀번호가 비어있습니다. ");
        }

        // validation logic...
    }

    private static String encode(String plainPassword){

        return passwordEncoder.encode(plainPassword);
    }
}
