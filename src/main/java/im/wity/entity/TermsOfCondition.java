package im.wity.entity;

import jakarta.persistence.*;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

@Entity
@Table(name = "terms_of_condition")
public class TermsOfCondition {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long userId;

}
