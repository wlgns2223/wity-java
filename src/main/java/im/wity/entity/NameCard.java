package im.wity.entity;

import im.wity.vo.PageName;
import im.wity.vo.PageNameConverter;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

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
@EqualsAndHashCode(callSuper = false)
public class NameCard extends BaseEntity{

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false,unique = true)
    @Convert(converter = PageNameConverter.class)
    private PageName pageName;

    @Column(nullable = false)
    private Boolean isDeleted;

    @OneToMany(mappedBy = "nameCard",cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Block> blocks;

    @OneToOne(cascade = CascadeType.ALL,orphanRemoval = true)
    @JoinColumn(name = "avatar_id")
    private Avatar avatar;


    @Builder
    private NameCard(User user, PageName pageName,Avatar avatar,Set<Block> blocks ){
        this.user = user;
        this.pageName = pageName;
        this.isDeleted = false;
        this.avatar = avatar;
        this.blocks = blocks;
    }

    public void addBlock(Block block){
        if(this.blocks == null){
            this.blocks = new LinkedHashSet<>();
        }

        if(this.blocks.isEmpty()){
            block.assignOrder(0);
        } else {
            block.assignOrder(this.blocks.size());
        }

        this.blocks.add(block);
        if(block.getNameCard() != this){
            block.setNameCard(this);
        }
    }

    public static NameCard create(User user, PageName pageName){
        NameCard nameCard = NameCard.builder()
                .user(user)
                .avatar(Avatar.init())
                .pageName(pageName)
                .build();

        nameCard.addBlock(Block.ofText("위티에 가입하신걸 축하드립니다."));
        nameCard.addBlock(Block.ofLink("https://wity.im"));
        return nameCard;
    }

}
