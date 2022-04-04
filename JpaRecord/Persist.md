## JPA 영속성 컨텍스트
-----------------------
#### jpa에서 가장 중요한 2가지
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
   
#### 엔티티 생명주기
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
      




