package im.wity.fixture;

import im.wity.entity.NameCard;
import im.wity.entity.User;
import im.wity.vo.PageName;

import java.util.LinkedHashSet;

public class NameCardFixture {
    private static PageName pageName = PageName.of("foo");

    public static NameCard create(User user){
        return NameCard.builder()
                .user(user)
                .pageName(pageName)
                .blocks(new LinkedHashSet<>())
                .build();
    }

    public static PageName getPageName() {
        return pageName;
    }

    public static void setPageName(PageName pageName) {
        NameCardFixture.pageName = pageName;
    }
}
