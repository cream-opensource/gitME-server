package gitME.auth.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

//@Builder
@NoArgsConstructor
@Data
public class SignUpDataDTO {

    private String kakaoId;
    private String name;
    private String phone;
    private String email;
    private String birthDate;
    private String gitAccessToken;
    private Map<String, String> externalLink;
    private Map<String, String> language;
}
