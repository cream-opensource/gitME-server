package gitME.database.repository;

import gitME.database.entites.ExternalLink;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExternalLinkRepository extends JpaRepository<ExternalLink, Integer> {
}
