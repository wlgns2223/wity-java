package im.wity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Null;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "avatar")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Avatar extends BaseEntity{

    public static final class Validation {
        public static final int MAX_LEN = 255;
    }

    @Column
    @Max(Validation.MAX_LEN)
    private String name;

    @Column
    @Max(Validation.MAX_LEN)
    private String bio;

    @Column
    private String image;

    @Column(nullable = false)
    private Boolean isFolded;


    @Builder
    private Avatar(String name, String bio, String image){
        this.name = name;
        this.bio = bio;
        this.isFolded = false;
        this.image = image;
    }

    public static Avatar init(){
        return new Avatar(null,null,null);
    }


}
