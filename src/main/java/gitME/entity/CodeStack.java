package gitME.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Entity
@Data
@IdClass(CodeStackId.class)
public class CodeStack {

    @Id
    private int userIdx;
    @Id
    private String language;
    private int codeCount;
}
