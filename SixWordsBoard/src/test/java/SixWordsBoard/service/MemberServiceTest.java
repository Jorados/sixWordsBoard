package SixWordsBoard.service;


import SixWordsBoard.DuplicatedIdEx;
import SixWordsBoard.domain.Member;
import SixWordsBoard.domain.Role;
import SixWordsBoard.repository.MemberJpaRepository;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

//@Runwith를 사용하는 이유 그냥 @SpringBootTest를 사용하면 application context를 전부 로딩해서 자칫 잘못하면 무거운 프로젝트가될수있다.
//하지만 @Runwith(SprintRunner.class)를 사용하면  @Autowired,@Bean에 해당되는것들에만 application context를 로딩하게 되므로 사용한다.


@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberService memberService;

    @Autowired
    MemberJpaRepository memberJpaRepository;

    @Autowired
    LoginService loginService;

    @Test
    public void 회원가입(){
        //given
        Member member = new Member();
        member.setLoginId("test2");
        member.setPassword("test2");
        member.setName("test2");
        member.setRole(Role.USER);
        //when
        Long findMemberId = memberService.join(member);
        //then
        Assertions.assertEquals(member,memberJpaRepository.findById(findMemberId));
    }
    
    @Test
    public void 조회(){
        
        //given
        Member member = getMember("test3","test3","test3",Role.ADMIN);
        //when
        Long findMemberId = memberService.join(member);  //가입해주고
        //then
        Member findMember = memberJpaRepository.findById(findMemberId);
        System.out.println("로그인 아이디 = {}" + findMember.getLoginId());  //Member객체의 LoginId조회
        System.out.println("이름 = {}" + findMember.getName());  //Member객체의 Name조회
    }

    private Member getMember(String loginId, String password, String name, Role role) {
        Member member = new Member();
        member.setLoginId(loginId);
        member.setPassword(password);
        member.setName(name);
        member.setRole(role);
        return member;
    }

    @Test(expected = DuplicatedIdEx.class)
    public void 동일아이디예외(){

        //given
        Member member1 = getMember("test4","test4", "test4",Role.ADMIN);
        Member member2 = getMember("test4","test4", "test4",Role.ADMIN);
        //when
        memberService.join(member1);
        memberService.join(member2);
        //then
        org.assertj.core.api.Assertions.fail("예외가 발생해야 한다.");
        
    }
}
