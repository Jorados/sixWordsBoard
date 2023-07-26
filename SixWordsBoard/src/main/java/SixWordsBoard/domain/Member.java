package SixWordsBoard.domain;

import SixWordsBoard.domain.element.Role;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Builder
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)  //테이블의 주키역할을 한다. 객체필드를 통해 직접접근 가능
    @Column(name = "member_id")
    private Long id;

    private String name;
    private String loginId;
    private String password;

    @Enumerated(EnumType.STRING)
    private Role role;

    @Builder
    public Member(Long id, String name, String loginId, String password, Role role) {
        this.id = id;
        this.name = name;
        this.loginId = loginId;
        this.password = password;
        this.role = role;
    }
}

