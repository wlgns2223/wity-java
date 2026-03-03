package im.wity.core;
import im.wity.entity.User;

public interface AuthManager {

    AuthResult process(User user);
}
