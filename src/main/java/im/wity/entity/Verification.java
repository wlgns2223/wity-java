package im.wity.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@Entity
@Table(name = "verification")
@EntityListeners(AutoCloseable.class)
public class Verification {

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
    @Column(updatable = false,nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column
    private LocalDateTime verifiedAt;

    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime expiredAt;

    @PrePersist
    private void setExpiredAt(){
        this.expiredAt = this.createdAt.plusMinutes(10);
    }

    public boolean isExpired(){
        return LocalDateTime.now().isAfter(expiredAt);
    }

}
