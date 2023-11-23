package gitME.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

import lombok.Data;

@Entity
@Data
public class GithubUser {

    @Id
    private int userIdx;
    private String accessToken;
    private String nickname;
    private int followers;
    private int following;
    private int totalStars;
    private int totalCommits;
    private String avatarUrl;
    private String refreshDate;

    @OneToMany(mappedBy = "githubUser")
    @JsonManagedReference
    private List<Repository> repository;
}
