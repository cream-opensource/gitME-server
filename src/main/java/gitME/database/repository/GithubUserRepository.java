package gitME.database.repository;

import gitME.database.entity.GithubUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GithubUserRepository extends JpaRepository<GithubUser, Integer> {
}
