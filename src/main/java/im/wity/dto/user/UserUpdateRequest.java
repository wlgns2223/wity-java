package im.wity.dto.user;

import im.wity.components.UserNameValidator;
import im.wity.vo.PageName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;

@Getter
public class UserUpdateRequest {

        @NotBlank
        @Pattern(
                regexp = PageName.Validation.VALID_PATTERN,
                message = PageName.Validation.MESSAGE
        )
        private String newDefaultPageName;

        @NotBlank
        @Pattern(
                regexp = UserNameValidator.VALID_PATTERN,
                message = UserNameValidator.MESSAGE
        )
        @Getter
        private String newUserName;


        public PageName getNewDefaultPageName(){
                return PageName.of(newDefaultPageName);
        }

}
