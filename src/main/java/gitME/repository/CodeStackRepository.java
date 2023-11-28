package gitME.repository;

import gitME.entity.CodeStack;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeStackRepository extends JpaRepository<CodeStack, Integer> {
    List<CodeStack> findByUserIdx(int userIdx);
}
