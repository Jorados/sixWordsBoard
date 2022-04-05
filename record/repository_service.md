## 2주차 기록
--------------------------------------------
1.repository패키지는 DB에 접근하는 모든 코드가 모여있다고 보면된다.                                
2.service패키지는 비즈니스 로직과 관련된 모든 코드가 모여있다고 보면된다.     

--->결론: DB접근과 관련된 문제가 발생하면 repository부분을 확인하고 , 비즈니스 로직과 관련된 부분에 문제가 발생했을 때는 service패키지를 확인하면 된다.

--------------------------------------------
### repository 개발(JPQL 쿼리문)

Member테이블에 대한 Repository

  *  여기서는  JPA 엔티티매니저(em)를 통한 영속성 컨텍스트라는 JPA가 관리하는 가상 컨테이너에 정보를 담아서 데이터베이스에 쿼리문을 쏴서 커밋하는 식으로 보면된다. 
  *  회원가입
     * em.persist()를 통해서 member를 영속시킨다.
  *  id로 회원 찾기
     * 회원가입할때 받는 id를 이용해 em.find()를 통해서 member의 회원을 찾는다
  *  로그인 id로 회원찾기   
     * em.createQuery("select m from Member m where m.loginId = :loginId", Member.class)로 로그인했을때 받는 id값으로 member.class의 loginId를 찾아서 있으면 결과반환 없으면 예외처리하는 식으로 개발
     

Board테이블에 대한 Repository

  * 글 작성 및 수정 : (FK)Id값을 받아와서 없으면 em.persist(board);로 영속 / 있으면 em.merge(board);로 업데이트  
  * 전체 조회 : em.createQuery("select b from Board b" , Board.class) 를 통해서 board테이블의 board 전체조회
  * 게시글 id로 게시글 찾기 : em.find(Board.class, boardId);를 통해서 영속되어 영속컨텍스트에 있는 board를 조회
  * member id로 글 조회 : em.createQuery("select b from Board b where b.member.id = : memberId" , Board.class) JPQL 작성
  * 작성자 이름으로 글 조회 : em.createQuery("select b from Board b where b.member.name = :name",Board.class) JPQL 작성
  * 키워드로 글 조회 :  em.createQuery("select b form Board b where b.content like '%" + keyword + "%'", Board.class) JPQL 작성 
  * 좋아요 높은 순으로 조회 :  em.createQuery("select b from Board b order by b.likeCount desc", Board.class) JPQL 작성 
  * 최신 순으로 조회 : em.createQuery("select b from Board b order by b.writeDate desc",Board.class) JPQL 작성
  * 글 삭제 : em.find(Board.class,id); 로 영속성 컨텍스트의 게시글 id를 빼와서 em.remove(findBoard)로 삭제
  * 내가 좋아요 누른 글 조회 : em.createQuery("select l.board from Likes l where l.member.id = :memberId", Board.class) JPQL 작성
  * 전체 글 수 :  ((Number) em.createQuery("select count(b) from Board b").getSingleResult()).intValue(); JPQL 작성

Likes테이블 대한 Repository
  * 좋아요 등록: em.persist(likes);
  * 좋아요 취소(회원 id와 게시글 id를 받아서 좋아요 찾고 취소): em.createQuery("select 1 from Likes 1 where 1.member.id = :memberId and 1.board.id = :boardId", Likes.class) 로 받아서 board.getLikes().remove(findLikes); em.remove(findLikes); 해주면 좋아요 취소
  * 회원과 게시글 객체를 받아서 좋아요 객체가 있는지 확인: em.createQuery("select 1 from Likes 1 where 1.member.id = :member amd 1.board = :board", Likes.class) 받아와서 없으면 예외처리 있으면 리턴

--------------------------------------------------------------------------
### Service 개발

Service 패키지에서는
1.@Service이라는 어노테이션을 붙혀준다. 이 어노테이션은 내부에서 자바로직을 처리해주는 거라고 이해하면 된다.
2.@Transaction이라는 어노테이션을 붙혀준다.이 어노테이션은 데이터베이스의 상태를 변경하는 작업 또는 한번에 수행되어야 하는 연산(비즈니스 로직)들을 의미한다.
begin, commit 을 자동으로 수행해주며 , 예외 발생 시 rollback 처리를 자동으로 수행해준다.

1.MemberService
   * 회원(이름 중복) 가입 - 회원이름이 중복되는 경우는 가입 불가 (@Transactional) id를 저장하는 작업이있기때문.
   * 특정 id로 가입 member가 있으면 - 예외처리(DuplicatedEx)

2.BoardService
   * 글 작성 및 수정 (@Transactional) boardJpaRepository.save(board);
   * member id로 글 조회 - 내가 쓴 글 찾기 return boardJpaRepository.findByBoardId(boardId);
   * board id로 글 조회 - return boardJpaRepository.findByBoardId(boardId);
   * 작성자 이름으로 글 조회 - return boardJpaRepository.findByName(name);
   * 키워드로 글 조회 - return boardJpaRepository.findByKeyword(keyword);
   * 좋아요 높은 순으로 조회 - return boardJpaRepository.findByLikesCnt(startIndex, pageSize);
   * 최신순 조회 - return boardJpaRepository.findByLatestDate(startIndex, pageSize);
   * 글 삭제 - (@Transactional)boardJpaRepository.delete(id);
   * 내가 좋아요 누른 글 조회 - return boardJpaRepository.findLikesBoard(memberId);
   * (board) 전체 조회 - return boardJpaRepository.findAll();
   * (board) 개수 조회 - return boardJpaRepository.findAllCnt();

3.LikesService
   *  좋아요 등록(좋아요는 한 게시물 당 한번만) -> 회원id,게시글id를 받아와서 해당 member가 해당 게시글에 좋아요 누른적있는지 확인 후에 안눌렀으면 좋아요등록(addLikeCount()) 눌렀으면 IllegalStateException 예외처리.
   *  해당 게시글 좋아요 -1,좋아요 취소 -> 글(회원,게시글id) 찾아서 좋아요취소(회원,게시글id,findBoard) (minusLikeCount()) 
   *  보드 아이디가 키,좋아요 눌려졌는지 안눌러졌는지 값 가져오기

4.LoginService 라는 로직서비스클래스 하나 만듦
   *  로그인 할 때 아이디쳤는데 loginId없으면 예외처리 / 비번쳣는데 일치하면 member반환,없으면 예외처리
   
