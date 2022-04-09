package SixWordsBoard.web;

import SixWordsBoard.domain.Board;
import SixWordsBoard.domain.Member;
import SixWordsBoard.domain.Pagination;
import SixWordsBoard.service.BoardService;
import SixWordsBoard.service.LikesService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class HomeController {

    private final BoardService boardService;
    private final LikesService likesService;

    @GetMapping("/")
    public String Home(@SessionAttribute(name = "loginMember", required = false) Member loginMember, Model model,
                       @RequestParam(defaultValue = "1")int page){

        int totalListCnt = boardService.findAllCnt();
        Pagination pagination = new Pagination(totalListCnt, page);  //총게시글수,현재페이지
        int startIndex = pagination.getStartIndex(); //게시글 불러오기
        int pageSize = pagination.getPageSize(); //게시판 당 띄울 게시글 수

        List<Board> orderedByL = boardService.findByLikesCnt(startIndex, pageSize); //좋아요높은순 담고
        List<Board> orderedByD = boardService.findByLatestDate(startIndex, pageSize); //최신순 담고

        model.addAttribute("orderedByL",orderedByL);   //좋아요순으로 게시글 불러오기
        model.addAttribute("orderedByD", orderedByD);  //최신순으로 게시글 불러오기
        model.addAttribute("pagination", pagination);  //페이지네이션 모델뷰전환

        if(loginMember == null){
            return "Home";
        }

        Map<Long,Integer> myLikeBoardId = likesService.getLikeBoardId(loginMember.getId(),orderedByL);

        model.addAttribute("memberId",loginMember.getId());
        model.addAttribute("myLikeBoardId",myLikeBoardId);
        return "loginHome";

    }



}

