package im.wity.dto.user;

import im.wity.vo.PageName;
import lombok.Builder;

@Builder
public record UserUpdate(PageName pageName,String userName) {
}
