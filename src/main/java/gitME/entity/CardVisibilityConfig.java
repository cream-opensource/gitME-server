package gitME.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

/**
 * File: CardVisibilityConfig
 * Author: making
 * Date: 2023-11-28
 * Desc: 카드 가시성 엔티티
 */
@Entity
@Getter @Setter
public class CardVisibilityConfig {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idx;

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
