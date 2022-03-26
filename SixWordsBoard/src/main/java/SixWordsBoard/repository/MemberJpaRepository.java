package SixWordsBoard.repository;


import SixWordsBoard.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

@Slf4j //로깅
@Repository
public class MemberJpaRepository {

    @PersistenceContext //영속성 컨텍스트
    EntityManager em; //엔티티매니저

    //회원 가입
    public Member save(Member member){
        em.persist(member);
        return member;
    }

    //id로 회원 찾기
    public Member findById(Long id){
        return em.find(Member.class, id);
    }

    //로그인 id로 회원찾기
    public Member findByLoginId(String loginId){

        Member findMember;
        try{
            findMember = em.createQuery("select m from Member m where m.loginId = :loginId", Member.class) //JPQL 쿼리 /공부하자
                    .setParameter("loginId",loginId)
                    .getSingleResult();
        }catch (NoResultException e){
            return null;
        }
        return findMember;
    }
}
