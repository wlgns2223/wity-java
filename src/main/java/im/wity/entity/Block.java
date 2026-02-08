package im.wity.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Getter
@Entity
@Table(name = "block")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Block {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Boolean folded;

    @Column(nullable = false)
    private Boolean active;

    @Column(nullable = false)
    private String type;

    @Column(nullable = false)
    private Integer clickCount;

    @Column(nullable = false)
    private Integer blockOrder;

    @JdbcTypeCode(SqlTypes.JSON)
    @Column(columnDefinition = "json")
    private Map<String, Object> customAttrs;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "name_card_id", nullable = false)
    private NameCard nameCard;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(nullable = false)
    private LocalDateTime updatedAt;

    @Builder
    public Block(String type, Map<String,Object> customAttrs, NameCard nameCard){
        this.folded = false;
        this.active = true;
        this.type = type;
        this.clickCount = 0;
        this.customAttrs = customAttrs;
        this.nameCard = nameCard;
        setNameCard(nameCard);
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

}
