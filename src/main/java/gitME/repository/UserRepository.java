package gitME.repository;

import gitME.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByIdx(int idx);

    Optional<User> findByKakaoId(String kakaoId);
}
