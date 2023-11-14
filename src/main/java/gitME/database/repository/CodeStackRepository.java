package gitME.database.repository;

import gitME.database.entity.CodeStack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CodeStackRepository extends JpaRepository<CodeStack, Integer> {
}
