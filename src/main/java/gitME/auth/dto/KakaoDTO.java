package gitME.auth.dto;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class  KakaoDTO {

    private long id;
    private String nickname;

}