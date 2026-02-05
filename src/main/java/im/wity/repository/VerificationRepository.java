package im.wity.repository;

import im.wity.entity.Verification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VerificationRepository extends JpaRepository<Verification,Long> {
}
