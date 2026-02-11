package im.wity.service;

import im.wity.components.PasswordFactory;
import im.wity.dto.UserCreate;
import im.wity.entity.User;
import im.wity.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordFactory passwordFactory;

    @Transactional
    public User createLocal(UserCreate userCreate){
        if(userRepository.existsUserByEmail(userCreate.email())){
            throw new IllegalArgumentException(userCreate.email() + "는 이미 가입된 이메일입니다.");
        }

        return userRepository.save(User.createLocalUser(
                userCreate.email(),
                passwordFactory.create(userCreate.password()),
                userCreate.defaultPageName(),
                userCreate.userName()
        ));
    }

    public void delete(Long id){
        if (!userRepository.existsUserById(id)) {
            throw new IllegalArgumentException("삭제하려는 아이디가 없습니다.");
        }

        userRepository.deleteById(id);
    }
}
