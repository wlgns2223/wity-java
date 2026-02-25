package im.wity.entity;

import im.wity.constant.BlockAttrKey;
import im.wity.constant.BlockType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Entity
@Table(name = "block")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EntityListeners(AuditingEntityListener.class)
@EqualsAndHashCode(exclude = "nameCard")
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean folded;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private BlockType type;

    @Column(nullable = false)
    private Integer clickCount;

    @Column(nullable = false)
    private Integer blockOrder;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private Map<BlockAttrKey, Object> customAttrs;

    @ManyToOne
    @JoinColumn(name = "name_card_id", nullable = false)
    @ToString.Exclude
    private NameCard nameCard;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Block(BlockType type, Map<BlockAttrKey,Object> customAttrs){
        this.folded = false;
        this.active = true;
        this.type = type;
        this.clickCount = 0;
        this.customAttrs = customAttrs;
    }

    protected void assignOrder(int order){
        this.blockOrder = order;
    }

    public void setNameCard(NameCard nameCard){
        if(this.nameCard == nameCard) return;

        if(this.nameCard != null){
            this.nameCard.getBlocks().remove(this);
        }
        this.nameCard = nameCard;
        nameCard.getBlocks().add(this);
    }

    public static Block ofText(String content){
        return Block.builder()
                .type(BlockType.TEXT)
                .customAttrs(Map.of(BlockAttrKey.KEY, content))
                .build();
    }

    public static Block ofLink(String content){
        return Block.builder()
                .type(BlockType.LINK)
                .customAttrs(Map.of(BlockAttrKey.KEY, content))
                .build();
    }

    public static Block from(BlockType type, Map<BlockAttrKey,Object> attrs){
        type.validate(attrs);
        return Block.builder()
                .type(type)
                .customAttrs(attrs)
                .build();

    }



}
