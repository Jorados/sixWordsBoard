package SixWordsBoard.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class LoginDto {

    @NotBlank
    String loginId;

    @NotBlank
    String password;

}
