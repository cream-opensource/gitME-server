package gitME.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.Date;
import java.util.List;

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
    private String createDate;
    private String updateDate;
}
