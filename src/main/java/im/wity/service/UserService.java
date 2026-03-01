package im.wity.service;

import im.wity.components.PasswordService;
import im.wity.components.UserNameValidator;
import im.wity.dto.user.UserCreate;
import im.wity.dto.user.UserUpdateRequest;
import im.wity.entity.User;
import im.wity.repository.UserRepository;
import im.wity.vo.PageName;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordService passwordService;
    private final NameCardService nameCardService;

    @Transactional
    public User createLocal(UserCreate userCreate){
        if(userRepository.existsUserByEmail(userCreate.email())){
            throw new IllegalArgumentException(userCreate.email() + "는 이미 가입된 이메일입니다.");
        }

        return userRepository.save(User.createLocalUser(
                userCreate.email(),
                passwordService.create(userCreate.password()),
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

    public Optional<User> findByEmail(String email){
        return userRepository.findByEmail(email);

    }

    @Transactional
    public User update(Long userId,UserUpdateRequest updateRequest){
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("존재하지 않는 회원입니다."));

        PageName newDefaultPageName = updateRequest.getNewDefaultPageName();
        if(!nameCardService.existsByUserAndPageName(user, newDefaultPageName)){
            throw new RuntimeException(newDefaultPageName + "이 없습니다.");
        }

        String newUserName = updateRequest.getNewUserName();
        UserNameValidator.validate(newUserName);
        user.update(newDefaultPageName, newUserName);
        return user;
    }
}
