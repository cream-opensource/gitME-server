package gitME.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Column;
import java.util.Date;
import lombok.Data;

@Entity
@Table(name = "GithubUserdata")
@Data
public class GithubUserdataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long user_id;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "followers")
    private int followers;

    @Column(name = "following")
    private int following;

    @Column(name = "total_stars")
    private int total_stars;

    @Column(name = "total_commits")
    private int total_commits;

    @Column(name = "update_date")
    private Date update_date;
}
