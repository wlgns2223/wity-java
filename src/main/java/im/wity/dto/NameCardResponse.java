package im.wity.dto;

import im.wity.entity.Avatar;
import im.wity.entity.NameCard;

import java.time.LocalDateTime;
import java.util.List;

public record NameCardResponse(
        Long id,
        UserResponse userResponse,
        String pageName,
        Boolean isDeleted,
        List<BlockResponse> blocks,
        Avatar avatar,
        LocalDateTime createdAt,
        LocalDateTime updatedAt
) {
    public static NameCardResponse from(NameCard nameCard){
        return new NameCardResponse(
                nameCard.getId(),
                UserResponse.from(nameCard.getUser()),
                nameCard.getPageName().getPageName(),
                nameCard.getIsDeleted(),
                nameCard.getBlocks()
                        .stream()
                        .map(BlockResponse::from)
                        .toList(),
                nameCard.getAvatar(),
                nameCard.getCreatedAt(),
                nameCard.getUpdatedAt()
                );
    }
}
