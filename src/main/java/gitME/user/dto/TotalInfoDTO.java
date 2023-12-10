package gitME.user.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Builder
@Data
public class TotalInfoDTO {
    // user
    private int userIdx;
    private String kakaoId;
    private String name;
    private String birthDate;
    private String email;
    private String phone;
    private String introduction;

    private Map<String, String> skill;
    private String skillProficiency;
    private Map<String, String> externalLink;

    // githubInfo
    private String nickname;
    private int followers;
    private int following;
    private int totalStars;
    private int totalCommits;
    private String avatarUrl;

    // codeStack
    private Map<String, Integer> languages;

}
