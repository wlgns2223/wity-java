package im.wity.dto.nameCard;

import im.wity.entity.User;
import im.wity.vo.PageName;

public record NameCardCreateRequest(String pageName) {
    public NameCardCreate toNameCardCreate(User user){
        return NameCardCreate.builder().user(user).pageName(PageName.of(pageName)).build();
    }
}
