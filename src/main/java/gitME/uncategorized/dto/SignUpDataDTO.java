package gitME.uncategorized.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

//@Builder
@NoArgsConstructor // 기본 생성자를 생성
@Data
public class SignUpDataDTO {

    private String kakaoId;
    private String name;
    private String phone;
    private String email;
    private String birthDate;
    private String gitAccessToken;
}
