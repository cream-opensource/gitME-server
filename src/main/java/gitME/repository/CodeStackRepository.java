package gitME.repository;

import gitME.entity.CodeStack;
import gitME.entity.CodeStackId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CodeStackRepository extends JpaRepository<CodeStack, CodeStackId> {
    List<CodeStack> findByUserIdx(int userIdx);
}
