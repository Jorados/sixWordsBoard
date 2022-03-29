package SixWordsBoard.web;


import SixWordsBoard.domain.Board;
import SixWordsBoard.domain.Likes;
import SixWordsBoard.domain.Member;
import SixWordsBoard.service.BoardService;
import SixWordsBoard.service.LikesService;
import SixWordsBoard.service.MemberService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class LikeController {

    private final LikesService likesService;
    private final MemberService memberService;
    private final BoardService boardService;


    @PostMapping("/like")
    @ResponseBody //서버응답
    public int like(@SessionAttribute(name = "loginMember",required = false) Member loginMember,   //loginMember를 받아서
                    @RequestParam("likeCheck") int likeCheck,    //요청받고
                    @RequestParam("boardId") Long boardId) {     //요청받아서

        log.info("likeCheck = " + likeCheck);   //로그찍고
        log.info("boardId = " + boardId);

        if(likeCheck == 1){
            log.info("좋아요가 눌러져 있는상태 .취소 로직 가동");
            likesService.unlike(loginMember.getId(),boardId);
        }
        else if(likeCheck ==0){
            log.info("좋아요가 안눌러져 있는 상태. 누르는 로직 가동");
            Likes like = likesService.like(loginMember.getId(),boardId);
        }

        Board findBoard = boardService.findByBoardId(boardId);
        int updateLikeCnt = findBoard.getLikeCount();

        return updateLikeCnt;

    }

}
