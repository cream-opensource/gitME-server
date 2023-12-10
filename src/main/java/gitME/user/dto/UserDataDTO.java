package gitME.user.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

/**
 * File: UserDataDTO
 * Author: making
 * Date: 2023-12-01
 * Desc: 유저 데이터 DTO
 */

@NoArgsConstructor
@Data
public class UserDataDTO {

    private int userIdx;
    private String kakaoId;
    private String name;
    private String birthDate;
    private String email;
    private String phone;
    private Map<String, String> externalLink;
    private Map<String, String> skill;
    private String skillProficiency;

}
