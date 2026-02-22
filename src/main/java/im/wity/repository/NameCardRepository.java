package im.wity.repository;

import im.wity.entity.NameCard;
import im.wity.entity.User;
import im.wity.vo.PageName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface NameCardRepository extends JpaRepository<NameCard,Long> {
    boolean existsByPageName(PageName pageName);

    NameCard findByPageName(PageName pageName);

    List<NameCard> findAllByUser(User user);
}
