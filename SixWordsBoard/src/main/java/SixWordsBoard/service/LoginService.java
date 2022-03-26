package SixWordsBoard.service;


import SixWordsBoard.FailedLoginEx;
import SixWordsBoard.domain.Member;
import SixWordsBoard.repository.MemberJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.security.auth.login.FailedLoginException;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginService {

    private final MemberJpaRepository memberJpaRepository;

    public Member login(String loginId,String password){

        Member member = memberJpaRepository.findByLoginId(loginId);
        if(member == null){     //아이디쳐서 찾았는데 null이면
            throw new FailedLoginEx("존재하지 않는 아이디입니다.");
        }

        if(member.getPassword().equals(password)){ // 비번일치하면
            return member;
        }else{  //아니면
            throw new FailedLoginEx("아이디 또는 비밀번호가 일치하지않습니다.");
        }

    }


}
