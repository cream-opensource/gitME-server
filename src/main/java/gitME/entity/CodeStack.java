package gitME.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class CodeStack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int userIdx;
    private String language;
    private int codeCount;

}
