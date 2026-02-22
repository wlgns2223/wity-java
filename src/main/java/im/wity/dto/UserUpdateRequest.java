package im.wity.dto;

import im.wity.components.UserNameValidator;
import im.wity.vo.PageName;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

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
        private String newUserName;


        public PageName getNewDefaultPageName(){
                return PageName.of(newDefaultPageName);
        }

        public String getNewUserName(){
                return newUserName;
        }
}
