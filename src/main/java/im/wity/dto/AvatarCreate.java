package im.wity.dto;

import im.wity.entity.Avatar;
import jakarta.validation.constraints.Max;

public record AvatarCreate(
        @Max(Avatar.Validation.MAX_LEN)
        String name,
        @Max(Avatar.Validation.MAX_LEN)
        String bio,
        String image) {

    public Avatar toEntity() {
        return Avatar.builder().name(name).bio(bio).image(image).build();
    }

}
