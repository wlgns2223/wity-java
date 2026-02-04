package im.wity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;
import java.util.Map;

@Getter
@Entity
@Table(name = "block")
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

    public void setNameCard(NameCard nameCard){
        if(this.nameCard == nameCard) return;

        if(this.nameCard != null){
            this.nameCard.getBlocks().remove(this);
        }
        this.nameCard = nameCard;
        nameCard.getBlocks().add(this);
    }

}
