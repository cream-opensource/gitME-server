package gitME.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Entity
@Data
public class Repository {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;
    private String name;

    @ManyToOne
    @JoinColumn(name = "user_idx")
    @JsonBackReference
    private GithubUser githubUser;

}
