package SixWordsBoard.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity @Getter
@Setter
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  //테이블의 주키역할을 한다. 객체필드를 통해 직접접근 가능
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String loginId;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

}

