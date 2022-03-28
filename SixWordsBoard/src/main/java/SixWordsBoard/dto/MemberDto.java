package SixWordsBoard.dto;


//dto란 >> 데이터 전송 객체 // 어디에서 어디로..?
// Dto는 웹 서비스의 클라이언트와 서버, 더 자세히는 클라이언트와 서버의 서비스 계층 사이에서 교환되는 데이터를 담는 그릇이다.
//데이터 전송할때 필요함 @PostMapping

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MemberDto {

    @NotBlank //@NotBlank 는 null 과 "" 과 " " 모두 허용하지 않는다. //@NotNull @NotEmpty 보다 가장 강력한 어노테이션
    private String name;
    @NotBlank
    private String loginId;
    @NotBlank
    private String password;


}
