package im.wity.dto;

import im.wity.entity.Avatar;
import im.wity.entity.User;
import im.wity.vo.PageName;

public record NameCardCreateDto(
        User user,
        PageName pageName,
        Avatar avatar
) { }