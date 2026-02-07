package im.wity.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "avatar")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Avatar {

    public static final class Validation {
        public static final int MAX_LEN = 255;
    }


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

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

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Avatar(String name, String bio, String image){
        this.name = name;
        this.bio = bio;
        this.isFolded = false;
        this.image = image;
    }


}
