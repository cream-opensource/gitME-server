package gitME.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class SkillId implements Serializable {
    private int userIdx;
    private String language;
}
