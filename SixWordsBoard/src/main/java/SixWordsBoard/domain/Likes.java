package SixWordsBoard.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class Likes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "likes_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;


    // 생성메서드
    public static Likes createLike(Member member, Board board){

        Likes likes = new Likes();
        likes.setMember(member);

        //연관관계 추가
        likes.setBoard(board);

        return likes;
    }

}
