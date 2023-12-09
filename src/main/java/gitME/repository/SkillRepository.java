package gitME.repository;

import gitME.entity.CardVisibilityConfig;
import gitME.entity.Skill;
import gitME.entity.SkillId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SkillRepository extends JpaRepository<Skill, SkillId> {
}
