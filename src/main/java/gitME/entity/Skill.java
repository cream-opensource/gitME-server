package gitME.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Entity
@Data
@IdClass(SkillId.class)
public class Skill {

    @Id
    private int userIdx;

    @Id
    private String language;

    private String detail;
    private String proficiency;

}
