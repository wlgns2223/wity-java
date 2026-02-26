package im.wity.entity;

import im.wity.constant.TermsOfCondType;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.Fetch;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@Entity
@Table(
        name = "terms_of_condition",
        uniqueConstraints = {
                @UniqueConstraint(
                        name = "UK_user_type",
                        columnNames = {"user_id","type"}
                )
        }
)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Builder
public class TermsOfCondition extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column
    private TermsOfCondType type;

    @Column
    private boolean agreed;

}
