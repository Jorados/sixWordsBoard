package SixWordsBoard.dto;


import SixWordsBoard.annotation.wordsLimit;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BoardDto {

    @NotBlank
    @wordsLimit
    private String content; //게시글

}
