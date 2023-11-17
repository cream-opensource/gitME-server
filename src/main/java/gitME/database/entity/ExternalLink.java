package gitME.database.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class ExternalLink {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;
    private int userIdx;
    private String url;
    private String description;
}
