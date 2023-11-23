package gitME.entity.vo;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;

/**
 * File: GitHubData.java
 * Author: making
 * Date: 2023-11-23
 * Desc: GitHub 데이터 VO
 */
@Builder
@Getter @Setter
public class GitHubData {

    private String nickname;                // 사용자 로그인 ID
    private String avatarUrl;               // 프로필 이미지 URL
    private int followers;                  // 팔로워 수
    private int following;                  // 팔로잉 수
    private int totalStars;                 // 스타를 준 저장소 수
    private int totalCommits;               // 사용자 총 커밋 수
    private Map<String, Integer> languages; // 저장소 사용 언어 정보 모음
}
