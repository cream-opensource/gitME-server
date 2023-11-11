package gitME.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import lombok.Data;

@Entity
@Table(name = "ExternalLinks")
@Data
public class ExternalLinksEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "link_id")
    private Long link_id;

    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "url")
    private String url;

    @Column(name = "description")
    private String description;
}
