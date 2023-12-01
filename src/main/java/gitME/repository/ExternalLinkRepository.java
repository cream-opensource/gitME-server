package gitME.repository;

import gitME.entity.ExternalLink;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExternalLinkRepository extends JpaRepository<ExternalLink, Integer> {
    List<ExternalLink> findByUserIdx(int userIdx);
}
