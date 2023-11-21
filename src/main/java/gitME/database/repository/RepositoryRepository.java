package gitME.database.repository;

import gitME.database.entites.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RepositoryRepository extends JpaRepository<Repository, Integer> {
}
