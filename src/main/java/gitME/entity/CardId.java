package gitME.entity;

import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardId implements Serializable {

    private int userIdx;
    private int templateIdx;
    private String color;
    private int sequence;
}
