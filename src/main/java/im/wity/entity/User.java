package im.wity.entity;

import jakarta.persistence.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "user")
public class User {
    @Id
    private Long id;

    @Column(nullable = false)
    String email;

    @Column
    String password;

    @Column(nullable = false)
    String loginProvider;

    @Column(nullable = false)
    Boolean isOauth;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createdAt;

    @LastModifiedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime updatedAt;





}
