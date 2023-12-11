package gitME.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import lombok.Data;

@Entity
@Data
@IdClass(CardId.class)
public class Card {

    @Id
    private int userIdx;

    @Id
    private int templateIdx;

    @Id
    private String color;

    @Id
    private int sequence;

}
