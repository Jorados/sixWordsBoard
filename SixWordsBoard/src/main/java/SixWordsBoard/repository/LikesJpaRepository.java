package SixWordsBoard.repository;


import SixWordsBoard.domain.Board;
import SixWordsBoard.domain.Likes;
import SixWordsBoard.domain.Member;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Repository
public class LikesJpaRepository {

    @PersistenceContext
    EntityManager em;

    //좋아요 등록 (회원 id와 게시글 id를 받아서 새로운 좋아요 객체 만든 다음에 persist)  //service 클래스에서 구현
    public Likes like(Likes likes){
        em.persist(likes);
        return likes;
    }
    //좋아요 취소 (회원 id와 게시글 id를 받아서 좋아요 찾고 취소) //service 클래스에서 구현
    public void unlike(Long memberId, Long boardId, Board board){

        Likes findLikes = em.createQuery("select l from Likes l where l.member.id = :memberId and l.board.id = :boardId", Likes.class)
                .setParameter("memberId",memberId)
                .setParameter("boardId",boardId)
                .getSingleResult();

        //좋아요 찾아서 취소
        board.getLikes().remove(findLikes);
        em.remove(findLikes); //컨텍스트에서 삭제
    }

    //회원과 게시글 객체를 받아서 좋아요 객체 있는지 확인
    public Likes findLike(Member member, Board board){
     Likes findLike;
     try{
         findLike = em.createQuery("select l from Likes l where l.member = :member and l.board = :board", Likes.class)
                 .setParameter("member",member)
                 .setParameter("board",board)
                 .getSingleResult();
     }
     catch (NoResultException e){   //없으면예외처리
         return null;
     }
        return findLike; //값있으면 리턴
    }
}
