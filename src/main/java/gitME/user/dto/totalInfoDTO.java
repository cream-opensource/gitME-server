package gitME.user.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class totalInfoDTO {
    // user
    public String kakaoId;
    public String name;
    public String birthDate;
    public String email;
    public String phone;

    // githibInfo
    public String nickname;
    public int followers;
    public int following;
    public int totalStars;
    public int totalCommits;
    public String avatarUrl;

}
