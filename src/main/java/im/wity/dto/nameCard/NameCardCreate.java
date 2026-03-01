package im.wity.dto.nameCard;

import im.wity.entity.User;
import im.wity.vo.PageName;
import lombok.Builder;


@Builder
public record NameCardCreate(
        User user,
        PageName pageName
) {

}