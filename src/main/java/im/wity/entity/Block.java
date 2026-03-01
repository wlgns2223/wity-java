package im.wity.entity;

import im.wity.constant.BlockAttrKey;
import im.wity.constant.BlockType;
import im.wity.dto.block.Size;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.util.Map;

@Getter
@Entity
@Table(name = "block")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode(exclude = {"nameCard","blockOrder"}, callSuper = false)
public class Block extends BaseEntity{

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
                .customAttrs(Map.of(BlockAttrKey.CONTENT, content))
                .build();
    }

    public static Block ofLink(String content){
        return Block.builder()
                .type(BlockType.LINK)
                .customAttrs(Map.of(BlockAttrKey.CONTENT, content))
                .build();
    }

    public static Block ofImage(String url, Size size){
        return Block.builder()
                .type(BlockType.IMAGE)
                .customAttrs(Map.of(BlockAttrKey.URL, url, BlockAttrKey.SIZE, size))
                .build();
    }

    public void updateCustomAttrs(Map<BlockAttrKey,Object> customAttrs){
        this.type.validate(customAttrs);
        this.customAttrs = customAttrs;
    }


}
