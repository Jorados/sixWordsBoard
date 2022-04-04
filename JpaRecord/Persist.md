##  :+1: JPA 영속성 컨텍스트
-----------------------
#### :star:jpa에서 가장 중요한 2가지
  * (JPA는 JAVA 애플리케이션과 JDBC사이에서 동작한다.)
  * 객체와 관계형 데이터베이스 매핑하기
  * 영속성 컨텍스트
  
      * JPA를 이해하는데 가장 중요한 용어
      * "엔티티를 영구 저장하는 환경"이라는 뜻
      * EntityManager.persist(entity);
      
        * 영속성 컨텍스트는 논리적인 개념
        * 눈에 보이지 않는다.
        * 엔티티 매니저(em)를 통해 영속성 컨텍스트에 접근
        
   즉, PersistenceContext라는 가상컨테이너에 em을 통해 접근하고 이 관계는 N:1 / 1:1 관계이다.
   em만이 PersistenceContext에 1혹은 N만큼의 참조를 할 수있다.
   
#### :star:엔티티 생명주기
   *  비영속,영속,준영속,삭제가 있다.
   *  비영속: 단순히 객체생성만 한 상태
      *  Member member = new Member();
      *  member.setId("member1");
      *  member.setUserName("회원1:);
   *  영속: 비영속 상태에서 객체를 em에 저장한 상태
      *  //불러와서
      *  EntityManager em = emf.createEntityManager();
      *  em.getTransaction().begin();
      *  //em을 통해서 객체를 컨텍스트에 저장
      *  em.persist(member);
   *  준영속(엔티티를 영속성 컨텍스트에서 분리),삭제(객체를 삭제)
      *  em.detach(member); //준영속(분리된) 상태 , em.clear() , em.close()
      *  em.remove(member); //삭제된 상태

#### :star:영속성 컨텍스트의 이점
   *  1차 캐시,동일성보장,트렌잭션을 지원하는 쓰기 지연,변경 감지,지연 로딩
      *  1차캐시: em.find로 member1을 찾으면 db를 찾는게아니라 em의 저장된 1차 캐시를 조회,만약 member2가 캐시에
없으면 db에서 캐시로 가져온다 -> 딱히 별 도움 안됨 ->(이유) UI요청이 하나 들어와서 비즈니스가끝나버리면 em을 다 지워버림 -> 다 날아감
   *  동일성 보장: Member의 a,b라는 객체가 em.find로 "member1"정보를 찾는다면 그 둘의 동일성 비교는 true다.
   *  트랜잭션 지원 쓰기 지연(쓰기 지연 SQL 저장소라는 곳이 있음)
       *  엔티티 매니저는 데이터 변경시에 트랜잭션을 시작해야한다
          *  EntityTransaction ts = em.getTransaction(); //불러오고
          *  transaction.begin(): //트랜잭션 시작
          *  여기서 em.pesist(~)로 영속을해도 INSERT SQL을 DB에 보내지않는다!!!!!!!
          *  transaction.commit();을 하는순간 쓰기지연 SQL저장소에 있는 정보가 DB에 날아가서 커밋된다.
 ![image](https://user-images.githubusercontent.com/100845256/161475810-5b01f223-bc85-4b85-8701-61634a1a540d.png)  
   *  변경감지(엔티티수정 / Dirty Checking)
      *  entity조회(em.find(Member.class,"member1");)하고
      *   memberA.setUsername("hi") / memberA.setAge(10); 영속되어있는 엔티티 데이터 수정하고
      *   em.utdate(member) //이런 코드가 필요할 것 같지만
      *   1차캐시 엔티티정보를 JPA 영속 컨텍스트에서 스냅샷으로 기록 후 기존 꺼랑 비교 후 쓰기지연 SQL저장소에 자동으로 update SQL을 생성 -> flush/commit 
   *  지연로딩
      *   JPQL로(Member findMember = em.createQuery("select m from Member m",Member.class).getSingleResult();) DB의 Member객체를 조회(Member 엔티티와 Team 엔티티는 N:1 매핑)한다고 가정해보자
      *   즉시로딩(@ManyToOne(fecth=FetchType.EAGER)를 사용하면 Member/Team을 조회하는 쿼리가 로그에 둘다 날아가는데 만약에 연관된 Team이 100개,1000개 이상이라면? 어지럽다. 그렇기 때문에 지연로딩@ManyToOne(fecth=FetchType.LAZY)을 사용하여 연관된 데이터만 불러오는 것이 좋다.


#### :star:플러시 flush()
   *  플러시:영속성 컨텍스트의 변경내용을 데이터베이스에 반영(새로고침이라고 생각하자)
   *  플러시 발생:변경감지,수정된엔티티 쓰기 지연SQL저장소에 등록,쓰기지연SQL저장소의쿼리를 DB에전송
   *  플러시 하는법:em.flush()-직접호출 / 트랜잭션 커밋-플러시자동호출 / JPQL-플러시자동호출
   *  플러시는!:영속성 컨텍스트를 비우는 개념이 아님,영속성 컨텍스트의 변경내용을 DB에 동기화,커밋직전에만 동기화 하면됨.

      




