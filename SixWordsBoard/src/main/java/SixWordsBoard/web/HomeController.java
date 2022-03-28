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
    public String home(@SessionAttribute(name = "loginMember", required = false) Member loginMember,
                       Model model,
                       @RequestParam(defaultValue = "1")int page){

        int totalListCnt = boardService.findAllCnt();
        Pagination pagination = new Pagination(totalListCnt, page);
        int startIndex = pagination.getStartIndex();
        int pageSize = pagination.getPageSize();

        List<Board> orderedByL = boardService.findByLikesCnt(startIndex, pageSize); //좋아요높은순 담고
        List<Board> orderedByD = boardService.findByLatestDate(startIndex, pageSize); //최신순 담고

        model.addAttribute("orderedByL",orderedByL);   //model에 추가 spring model을 이용하여 view에 데이터 넘겨주기
        model.addAttribute("orderedByD", orderedByD);
        model.addAttribute("pagination", pagination);

        if(loginMember == null){
            return "home";
        }

        Map<Long,Integer> myLikeBoardId = likesService.getLikeBoardId(loginMember.getId(),orderedByL);

        model.addAttribute("memberId",loginMember.getId());
        model.addAttribute("myLikeBoardId",myLikeBoardId);

        return "loginHome";

    }



}

