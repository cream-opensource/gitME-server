package gitME.user.dto;

import lombok.Data;

/**
 * File: CardVisibilityConfigDTO
 * Author: making
 * Date: 2023-11-28
 * Desc: 카드 가시성 데이터 DTO
 */

@Data
public class CardVisibilityConfigDTO {
    private int userIdx;
    private Boolean nameVisibility;
    private Boolean birthDateVisibility;
    private Boolean phoneVisibility;
    private Boolean nicknameVisibility;
    private Boolean followersVisibility;
    private Boolean followingVisibility;
    private Boolean totalStarsVisibility;
    private Boolean totalCommitsVisibility;
    private Boolean avatarUrlVisibility;
    private Boolean codeStackVisibility;
    private Boolean externalLinkVisibility;
}
