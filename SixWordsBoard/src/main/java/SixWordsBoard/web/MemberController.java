package SixWordsBoard.web;


import SixWordsBoard.DuplicatedIdEx;
import SixWordsBoard.SessionConst;
import SixWordsBoard.domain.Board;
import SixWordsBoard.domain.Member;
import SixWordsBoard.domain.Role;
import SixWordsBoard.dto.MemberDto;
import SixWordsBoard.service.BoardService;
import SixWordsBoard.service.LikesService;
import SixWordsBoard.service.MemberService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
@AllArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final BoardService boardService;
    private final LikesService likesService;

    @ExceptionHandler(DuplicatedIdEx.class) //예외처리핸들러 //컨트롤러 내에서 발생하는 예외를 잡아서 하나의 메서드에서 처리해주는 기능을 한다.
    public String HandlerException(Exception ex, HttpServletRequest request, Model model){

        model.addAttribute("exMessage",ex.getMessage());
        model.addAttribute("redirectURI",request.getRequestURI());

        log.info("exMessage = " + ex.getMessage());  //로그에 에러메세지 나타내기
        log.info("redirectURI = " + request.getRequestURI());

        return "error/DuplicatedIdEx-redirect";
    }

    @GetMapping("/member/new")
    public String createJoinForm(Model model){
        model.addAttribute("member",new Member());
        return "member/new";  //회원가입창
    }

    @PostMapping("/member/new")
    public String join(@Validated @ModelAttribute("member") MemberDto dto, BindingResult bindingResult, //에러담는거
                       Model model, RedirectAttributes redirectAttributes, HttpServletRequest request){

        //만약에 에러뜨면 로그에띄우고 다시 회원가입창
        if(bindingResult.hasErrors()){
            log.info("binding Error = " + bindingResult);
            return "member/new";
        }

        Member member = new Member();
        member.setLoginId(dto.getLoginId()); //PostMapping에 의한 Dto로 데이터 전송받은걸 set함
        member.setPassword(dto.getPassword());
        member.setName(dto.getName());
        member.setRole(Role.ADMIN);

        memberService.join(member);      //맴버서비스 회원가입

        // 로그인 처리
        HttpSession session = request.getSession(true);
        session.setAttribute(SessionConst.LOGIN_MEMBER, member);

        redirectAttributes.addAttribute("memberName", member.getName());
        model.addAttribute("member", member);

        return "redirect:/member/afternew/{memberName}";
    }

    @GetMapping("/member/afternew/{memberName}")
    public String afterJoin(@PathVariable("memberName") String memberName, Model model){
        model.addAttribute("memberName",memberName);
        return "member/afternew";
    }

    @GetMapping("/member/{id}")  //내 id 고유번호 //로그인아이디아님
    public String createMypageForm(@PathVariable("id") Long memberId,Model model){

        List<Board> writeBoards = boardService.findByMemberId(memberId);  //쓴 글
        List<Board> likesBoard = boardService.findLikesBoard(memberId);  //좋아요 누른 글
        Map<Long,Integer> myLikeBoardId = likesService.getLikeBoardId(memberId,writeBoards);

        model.addAttribute("writeBoards",writeBoards); //모델 뷰에 데이터전송
        model.addAttribute("likeBoards", likesBoard);
        model.addAttribute("myLikeBoardId", myLikeBoardId);
        model.addAttribute("memberId", memberId);

        return "member/mypage";

    }


}
