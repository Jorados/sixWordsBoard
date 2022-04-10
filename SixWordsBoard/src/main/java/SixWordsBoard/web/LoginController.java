package SixWordsBoard.web;


import SixWordsBoard.FailedLoginEx;
import SixWordsBoard.SessionConst;
import SixWordsBoard.domain.Member;
import SixWordsBoard.dto.LoginDto;
import SixWordsBoard.service.LoginService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequiredArgsConstructor //롬복
@Slf4j //중간중간 로그확인가능
public class LoginController {

    private final LoginService loginService;

    @ExceptionHandler(FailedLoginEx.class)
    public String HandlerException(Exception ex, HttpServletRequest request, Model model){

        model.addAttribute("exMessage",ex.getMessage()); //mvc전송
        model.addAttribute("redirectURI",request.getRequestURI()); //경로전송

        log.info("exMessage",ex.getMessage());
        log.info("redirectURI",ex.getMessage());

        return "error/DuplicatedIdEx-redirect";
    }

    @GetMapping("/member/login")
    public String createLoginForm(@ModelAttribute("loginForm") LoginDto form){
    //@ModelAttribute 쓰면 자동으로 해당 객체를 addAttribute해서 해줌
    return "member/login";
    }

    @PostMapping("/member/login")
    public String loginForm(@Validated @ModelAttribute("loginForm") LoginDto dto, BindingResult bindingResult,
                            @RequestParam(name="redirectURL",defaultValue = "/") String redirectURL,
                            HttpServletRequest request){

        if(bindingResult.hasErrors()){
            log.info("bindingResult = " + bindingResult);
            return "member/login";
        }

        log.info("id = " + dto.getLoginId());
        log.info("password = " + dto.getPassword());

        Member loginMember = loginService.login(dto.getLoginId(), dto.getPassword()); //아디 비번 받아와서

        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.LOGIN_MEMBER, loginMember);  //받아온 loginMember 세션처리

        return "redirect:" + redirectURL; //다시 원래창으로
    }

    @GetMapping("/member/logout")   //이창 드가면 세션 false처리 하고 다시 원래창으로
    public String logout(HttpServletRequest request){

        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/";
    }
}
