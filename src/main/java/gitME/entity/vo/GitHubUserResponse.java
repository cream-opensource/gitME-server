package gitME.entity.vo;

import com.google.gson.annotations.SerializedName;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * File: GitHubUserResponse.java
 * Author: making
 * Date: 2023-11-23
 * Desc: GitHub 사용자 기본 정보 응답 VO
 */
@Getter @Setter
public class GitHubUserResponse {

    private String login;          // 사용자 로그인 ID
    @SerializedName("avatar_url")
    private String avatarUrl;      // 프로필 이미지 URL
    private String name;           // 사용자 이름
    @SerializedName("public_repos")
    private int publicRepos;       // 공개 저장소 수
    @SerializedName("public_gists")
    private int publicGists;       // 공개 Gists 수
    private int followers;         // 팔로워 수
    private int following;         // 팔로잉 수
    @SerializedName("private_gists")
    private int privateGists;      // 비공개 Gists 수
    @SerializedName("total_private_repos")
    private int totalPrivateRepos; // 비공개 저장소 총계
    @SerializedName("owned_private_repos")
    private int ownedPrivateRepos; // 소유한 비공개 저장소 수
    @SerializedName("disk_usage")
    private int diskUsage;         // 디스크 사용량
    private int collaborators;     // 협업자 수
    @SerializedName("created_at")
    private Date createdAt;        // 생성일
    @SerializedName("updated_at")
    private Date updatedAt;        // 수정일
}
