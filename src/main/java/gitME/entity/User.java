package gitME.entity;

import jakarta.persistence.*;

import java.util.Date;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;
    private String kakaoId;
    private String name;
    private Date birthDate;
    private String gender;
    private String email;
    private String phone;
    private Date createDate;
    private Date updateDate;
}
