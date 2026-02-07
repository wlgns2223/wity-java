package im.wity.entity;

import im.wity.vo.PageName;
import im.wity.vo.PageNameConverter;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.LinkedHashSet;
import java.util.Set;

@Getter
@Entity
@Table(name = "name_card",
        uniqueConstraints = {
        @UniqueConstraint(
                name = "uk_name_card_page_name",
                columnNames = {"page_name"}
        )
        }
)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class NameCard {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    @Convert(converter = PageNameConverter.class)
    private PageName pageName;

    @Column(nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "nameCard")
    private Set<Block> blocks = new LinkedHashSet<>();

    @OneToOne
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public NameCard(User user, PageName pageName,Avatar avatar ){
        this.user = user;
        this.pageName = pageName;
        this.isDeleted = false;
        this.avatar = avatar;
    }

    public void addBlock(Block block){
        this.blocks.add(block);
        if(block.getNameCard() != this){
            block.setNameCard(this);
        }
    }

}
