package SixWordsBoard.web;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ErrorTestController {

    @GetMapping("/500")
    public String error500(){
        throw new RuntimeException("에러발생!"); //실행중에발생 시스템환경적으로나 인풋값이 잘못된경우
    }

    @GetMapping("/400")
    public String error400(){
        throw new IllegalStateException("에러발생!"); //Controller의 @RequestMapping 의 값이 중복되어 나타나는 에러
    }
}
