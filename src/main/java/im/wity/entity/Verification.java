package im.wity.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "verification")
public class Verification extends BaseEntity {

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
