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
    public String createEditForm(@RequestParam("boardId") Long boardId,
                                 @SessionAttribute(name = " loginMember",required = false) Member loginMember, Model model){

        Board findBoard = boardService.findByBoardId(boardId);

        if(!isLoginMemberhteWriter(loginMember.getId(), findBoard.getMember().getId())) {
            //가져온 id가 게시판 작성 id 작성자가 아니면
            throw new NoAuthorizationEx("수정 권한이 없습니다."); //처리
        }

        model.addAttribute("board",findBoard);
        model.addAttribute("memberId",loginMember.getId());

        return "board/edit";
    }

    private boolean isLoginMemberhteWriter(Long loginMemberId, Long writerId) {
        return loginMemberId.equals(writerId); //loginMemberId랑 writerId랑 같은지
    }

    @PostMapping("/board/edit")
    public String edit(@Validated @ModelAttribute("board") BoardDto dto, BindingResult bindingResult,
                       @RequestParam("boardId") Long boardId,
                       RedirectAttributes redirectAttributes){
        log.info("binding error = " + bindingResult);
        log.info("dto = " + dto);

        if(bindingResult.hasErrors()){    //BindingResult //에러있으면 원래창으로
            log.info("binding error = " + bindingResult);
            return "board/edit" ;
        }

        Board findBoard = boardService.findByBoardId(boardId);

        findBoard.setContent(dto.getContent());  //set
        boardService.save(findBoard);  //저장

        redirectAttributes.addAttribute("boardId",findBoard.getId());   //중복 요청 방지
        return "redirect:/board"; //고쳐서 원래 보드
    }




    @GetMapping("/board/delete")
    public String createDeleteForm(@RequestParam("boardId") Long boardId ,Model model){
        model.addAttribute("boardId", boardId);
        return "board/delete";
    }

    @PostMapping("/board/delete")
    public String delete(@RequestParam("boardId")Long boardId,
                         //핸들러 메소드 // Session은 여러 화면이나 여러 요청에서 사용해야 하는 객체를 공유할 때 사용할 수 있다,
                         @SessionAttribute(name = "loginMember")Member loginMember){

        log.info("boarId" + boardId);
        log.info("삭제하려는 멤버 아이디" + loginMember.getId());

        Board findBoard = boardService.findByBoardId(boardId);

        if(!loginMember.getId().equals(findBoard.getMember().getId()) ){
            throw new NoAuthorizationEx("삭제 권한이 없음 ");
        }

        boardService.delete(boardId);

       return "redirect:/";  //삭제하고나면 홈으로
    }

    

}
