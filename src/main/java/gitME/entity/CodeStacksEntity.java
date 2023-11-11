package gitME.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;

@Entity
@Table(name = "CodeStacks")
@Data
public class CodeStacksEntity {

    @Id
    @Column(name = "repo_id")
    private Long repo_id;

    @Column(name = "language")
    private String language;

    @Column(name = "codelines")
    private int codelines;
}
