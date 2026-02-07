package im.wity.repository;

import im.wity.entity.NameCard;
import im.wity.vo.PageName;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NameCardRepository extends JpaRepository<NameCard,Long> {
    boolean existsByPageName(PageName pageName);
}
