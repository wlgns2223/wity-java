package im.wity.dto;

import im.wity.entity.Avatar;
import im.wity.entity.Block;
import im.wity.entity.User;
import im.wity.vo.PageName;
import lombok.Builder;

import java.util.LinkedHashSet;
import java.util.Set;


public record NameCardCreate(
        User user,
        PageName pageName,
        Avatar avatar,
        Set<Block> blocks
) {
    @Builder
    public NameCardCreate(User user, PageName pageName, Avatar avatar) {
        this(user, pageName, avatar, new LinkedHashSet<>());
    }
}