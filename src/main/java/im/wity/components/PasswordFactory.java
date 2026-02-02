package im.wity.components;

import im.wity.vo.Password;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class PasswordFactory  {

    private final PasswordEncoder passwordEncoder;

    public Password create(String plainPassword){
        return Password.Of(plainPassword, passwordEncoder);
    }

}
