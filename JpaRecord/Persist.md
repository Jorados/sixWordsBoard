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
      *  em.detach(member); //준영속(분리된) 상태
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
          *  transaction.commit();을 하는순간 DB에 persist된 객체가 커밋된다.
          *    

      




