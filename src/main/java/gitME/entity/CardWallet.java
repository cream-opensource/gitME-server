package gitME.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Entity
@Data
@IdClass(CardWalletId.class)
public class CardWallet {

    @Id
    private int userIdx;

    @Id
    private int targetUserIdx;

    @Id
    private int template;

}