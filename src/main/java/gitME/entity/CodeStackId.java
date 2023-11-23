package gitME.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class CodeStackId implements Serializable {
    private int userIdx;
    private String language;
}