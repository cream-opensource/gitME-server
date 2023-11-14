package gitME.database.repository;

import gitME.database.entity.ExternalLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExternalLinkRepository extends JpaRepository<ExternalLink, Integer> {
}
