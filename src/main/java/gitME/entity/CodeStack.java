package gitME.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CodeStack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;
    private String language;
    private int codeCount;

    @ManyToOne
    @JoinColumn(name = "repo_idx")
    @JsonBackReference
    private Repository repository;
}
