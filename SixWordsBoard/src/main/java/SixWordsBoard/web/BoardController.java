package SixWordsBoard.web;


import SixWordsBoard.NoAuthorizationEx;
import SixWordsBoard.domain.Board;
import SixWordsBoard.domain.Member;
import SixWordsBoard.dto.BoardDto;
import SixWordsBoard.service.BoardService;
import SixWordsBoard.service.LikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Controller
@Slf4j
@RequiredArgsConstructor
@Validated
public class BoardController {

    private final BoardService boardService;
    private final LikesService likesService;

    @ExceptionHandler(NoAuthorizationEx.class)  //에러 처리
    public String HandlerException(Exception ex, HttpServletRequest request , Model model){

        model.addAttribute("exMessage",ex.getMessage());
        model.addAttribute("queryParam",request.getQueryString());

        log.info("exMessage = " + ex.getMessage());
        log.info("queryParam = " + request.getQueryString());

        return "error/NoAuthorizationEx-redirect";
    }

    @GetMapping("/board/new")  //게시글 쓰는창
    public String createWriteForm(Model model, @SessionAttribute(name="loginMember",required = false) Member loginMember){

        model.addAttribute("board",new Board());
        model.addAttribute("memberId",loginMember.getId());

        return "board/save";  // 저장

    }

    @PostMapping("/board/new")
    public String write(@Validated @ModelAttribute("board")BoardDto dto, BindingResult bindingResult,
                        @SessionAttribute(name = "loginMember",required = false) Member loginMember,
                        RedirectAttributes redirectAttributes){

        if(bindingResult.hasErrors()){
            log.info("binding error = " + bindingResult);
            return "board/save";
        }

        Board board = new Board();
        board.setMember(loginMember);
        board.setContent(dto.getContent());
        board.setWriteDate(LocalDateTime.now());

        boardService.save(board);

        redirectAttributes.addAttribute("boardId", board.getId()); //url에 노출시키지않고 전송.?
        //POST/GET 방식으로 예약생성같은 DB 작업이 있는 페이지를 끝내고 결과페이지를 일반적으로 포워딩할경우 F5나 새로고침등으로 서버에 동일한
        // 요청을 보내어 문제가 발생할 가능성이 있어 주요 서비스에는 !!!!중복 요청이 들어가지 않도록 결과는 redirect 할수있도록 처리!!!가 필요합니다.
        //스프링 웹 mvc 따로 공부하자 ;;
        return "redirect:/board";
    }

    @GetMapping("/board")
    public String board(@RequestParam("boardId") Long boardId,
                        @SessionAttribute(name = "loginMember", required = false)Member loginMember,
                        Model model) {

        Board findBoard = boardService.findByBoardId(boardId);  //boarId로 찾아서
        model.addAttribute("board", findBoard);  //모델뷰 mvc전환

        if (loginMember == null) {
            return "board/board";
        }

        List<Board> boards = boardService.findAll();
        Map<Long , Integer> myLikeBoardId = likesService.getLikeBoardId(loginMember.getId(),boards);

        model.addAttribute("memberId", loginMember.getId());
        model.addAttribute("myLikeBoardId", myLikeBoardId);

        return "board/loginBoard";


    }
    @GetMapping("/board/edit")
    @PostMapping("/board/edit")
    @GetMapping("/board/delete")
    @PostMapping("/board/delete")
}
