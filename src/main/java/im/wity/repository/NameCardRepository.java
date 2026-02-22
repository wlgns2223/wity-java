package im.wity.repository;

import im.wity.entity.NameCard;
import im.wity.entity.User;
import im.wity.vo.PageName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NameCardRepository extends JpaRepository<NameCard,Long> {
    boolean existsByPageName(PageName pageName);

    NameCard findByPageName(PageName pageName);

    List<NameCard> findAllByUser(User user);

    Optional<NameCard> findByUserAndPageName(User user, PageName pageName);

    boolean existsByUserAndPageName(User user, PageName pageName);
}
