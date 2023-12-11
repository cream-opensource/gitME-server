package gitME.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardWalletId implements Serializable {

    private int userIdx;

    private int targetUserIdx;

    private int template;

}