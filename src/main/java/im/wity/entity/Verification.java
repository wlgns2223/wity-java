package im.wity.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(name = "verification")
@EntityListeners(AuditingEntityListener.class)
public class Verification  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String target;

    @Column(nullable = false)
    private String payload;

    @Column(nullable = false)
    private String event;

    @Column(nullable = false)
    private Boolean isVerified;

    @Column(nullable = false)
    private String code;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime verifiedAt;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime expiredAt;

    @PrePersist
    private void setExpiredAt(){
        this.expiredAt = getCreatedAt().plusMinutes(10);
    }

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expiredAt);
    }

}
