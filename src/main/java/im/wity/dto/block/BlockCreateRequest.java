package im.wity.dto.block;

import im.wity.constant.BlockType;
import jakarta.validation.constraints.NotNull;

public record BlockCreateRequest(@NotNull BlockType blockType) { }
