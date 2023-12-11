package gitME.user.dto;

import lombok.Data;

@Data
public class CardDTO {
    private int userIdx;
    private int templateIdx;
    private String color;
    private int sequence;
}
