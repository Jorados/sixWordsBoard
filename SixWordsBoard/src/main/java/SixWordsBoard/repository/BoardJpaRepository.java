package SixWordsBoard.repository;


import SixWordsBoard.domain.Board;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class BoardJpaRepository {

    @PersistenceContext
    EntityManager em;

    //글 작성 및 수정
    public void save(Board board){
        if(board.getId() == null){
            em.persist(board);  //새로운 board객체를 저장
        }else{
            em.merge(board); //이미 저장되어 있던 객체를 업데이트 // merge : 업데이트라고 생각하자
        }
    }

    //전체조회
    public List<Board> findAll(){
        return em.createQuery("select b form Board b" , Board.class)
                .getResultList();
    }

    //게시글 id로 게시글 찾기
    public Board findByBoardId(Long boardId){
        return em.find(Board.class, boardId);
    }

    //member id로 글 조회 - 내가 쓴 글 찾기
    public List<Board> findByMemberId(Long memberId){
        return em.createQuery("select b from Board b where b.member.id = : memberId" , Board.class)
                .setParameter("memberId",memberId)
                .getResultList();
    }

    //작성자 이름으로 글 조회
    public List<Board> findByName(String name){
        return em.createQuery("select b from Board b where b.member.name = :name",Board.class)
                .setParameter("name",name)
                .getResultList();
    }

    //키워드로 글 조회
    //좋아요 높은 순으로 조회
    //최신 순으로 조회
    //글 삭제
    //내가 좋아요 누른 글 조회
    //페이징 전용 메소드
    //전체 글 수
    //작성자 이름으로 조회딘 글 수
    //키워드로 조회된 글 수


}
