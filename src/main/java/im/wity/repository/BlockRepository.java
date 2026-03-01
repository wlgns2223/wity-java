package im.wity.repository;

import im.wity.entity.Block;
import im.wity.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BlockRepository extends JpaRepository<Block,Long> {
    Optional<Block> findByIdAndNameCard_Id(Long id, Long nameCardId);
    Optional<Block> findByIdAndNameCard_IdAndNameCard_User(Long id, Long nameCardId, User nameCardUser);
}
