package SixWordsBoard.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
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
        Likes likes = Likes.builder()
                .member(member)
                .board(board)
                .build();

        return likes;
    }

    @Builder
    public Likes(Long id, Member member, Board board) {
        this.id = id;
        this.member = member;
        this.board = board;
    }
}
