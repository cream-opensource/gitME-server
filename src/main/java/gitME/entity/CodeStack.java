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
    @JoinColumn(name = "repo_idx") // 매핑 왜 못해.. 작동은 잘 됨 문제 없음
    @JsonBackReference
    private Repository repository;
}
