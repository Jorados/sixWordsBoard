package SixWordsBoard.service;


import SixWordsBoard.domain.Board;
import SixWordsBoard.repository.BoardJpaRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@AllArgsConstructor
public class BoardService {
    private final BoardJpaRepository boardJpaRepository;

    //글 작성 및 수정
    @Transactional
    public void save(Board board){
        boardJpaRepository.save(board);
    }

    //member id로 글 조회 -내가 쓴 글 찾기
    public List<Board> findByMemberId(Long memberId){
        return boardJpaRepository.findByMemberId(memberId);
    }

    //board id로 글 조회 - 내가 쓴 글  찾기
    public Board findByBoardId(Long boardId){
        return boardJpaRepository.findByBoardId(boardId);
    }

    //작성자 이름으로 글 조회 - 검색
    public List<Board> findByName(String name){
        return boardJpaRepository.findByName(name);
    }

    //키워드로 글 조회
    public List<Board> findByKeyword(String keyword){
        return boardJpaRepository.findByKeyword(keyword);
    }

    // 좋아요 높은 순으로 조회
    public List<Board> findByLikesCnt(int startIndex, int pageSize){
        return boardJpaRepository.findByLikesCnt(startIndex, pageSize);
    }

    // 최신순 조회
    public List<Board> findByLatestDate(int startIndex, int pageSize){
        return boardJpaRepository.findByLatestDate(startIndex, pageSize);
    }

    // 글 삭제
    @Transactional
    public void delete(Long id){
        boardJpaRepository.delete(id);
    }

    // 내가 좋아요 누른 글 조회
    public List<Board> findLikesBoard(Long memberId){
        return boardJpaRepository.findLikesBoard(memberId);
    }


    public List<Board> findAll() {
        return boardJpaRepository.findAll();
    }

    public int findAllCnt() {
        return boardJpaRepository.findAllCnt();
    }
}
