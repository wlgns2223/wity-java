package im.wity.dto.user;

import im.wity.vo.PageName;
import im.wity.vo.Password;
import lombok.Builder;

@Builder
public record UserCreateLocal(String email,
                              Password encodedPassword,
                              PageName defaultPageName,
                              String userName
)

{
}
