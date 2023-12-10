package gitME.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;
    private String kakaoId;
    private String name;
    private String birthDate;
    private String email;
    private String phone;
    private String introduction;
    private String createDate;
    private String updateDate;
}
