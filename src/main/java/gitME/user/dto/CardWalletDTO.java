package gitME.user.dto;

import lombok.Data;
import lombok.Getter;

@Data
public class CardWalletDTO {
    private int userIdx;
    private int targetUserIdx;
    private int template;
}
