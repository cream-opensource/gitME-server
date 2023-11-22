package gitME.repository;

import gitME.entity.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRepository extends JpaRepository<Repository, Integer> {
}
