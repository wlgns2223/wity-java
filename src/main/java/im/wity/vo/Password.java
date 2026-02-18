package im.wity.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Password {

    @Column(name = "password")
    String encryptedPassword;

    private Password(String password){
        this.encryptedPassword = password;
    }

    public static Password Of(String plain, PasswordEncoder passwordEncoder){
        validate(plain);
        return new Password(encode(plain,passwordEncoder));
    }

    private static void validate(String plainPassword){
        if(plainPassword == null || plainPassword.isEmpty()){
            throw new IllegalArgumentException("비밀번호가 비어있습니다. ");
        }

        if(plainPassword.length() < 4){
            throw new IllegalArgumentException("비밀번호는 4자리 이상이어야합니다.");
        }
    }

    private static String encode(String plainPassword,PasswordEncoder passwordEncoder){

        return passwordEncoder.encode(plainPassword);
    }

    public boolean matches(String password,PasswordEncoder encoder){
        return encoder.matches(password, encryptedPassword);
    }
}
