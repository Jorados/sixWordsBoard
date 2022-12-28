### :clipboard: sixWordBoard    
---
        
1. 프로젝트        
    + 스프링부트와 jpa를 활용한 웹 애플리케이션 게시판(헤밍웨이가 6단어만으로 쓴 소설처럼 6단어로만 쓸 수 있는 게시판)

2. 개요
   + 명칭 : sixWordBoard
   + 개발자 : 조성진
   + 개발기간 : 2022-03-19 ~ ing.      
   + 사용언어 : JAVA 11(백) / html(thymeleaf),부트스트랩(프론트)   
   + 개발환경 : SpringBoot 2.6.4 / jpa / Mysql(관계형데이터베이스RDBMS)

3. 구현기능
   + (핵심)기본적인 게시글 crud(생성,조회,수정,삭제)기능
   + (핵심)설계한 도메인과 SpringBoot,JPA를 기반으로 한 기본적인 crud rest api
   + 헤밍웨이 게시판 컨셉에 맞는 6단어 제한

 
4. 서버환경
   + heroku 무료 클라우드 서버 서비스에 DB(MYSQL)연동 후 배포 

  
5. 개발방향
   + 게시판과 기능들을 먼저 구현하고 서버환경을 설정하는 방향으로 진행하겠습니다.
   + 엔티티개발(domain/데이터아키텍쳐) -> repository개발(DB접근) -> service개발(기능구현) -> webController개발(MVC) -> 프론트개발 -> 기본 게시판 개발 -> 서버 배포   



---------
### ⭐ 스프링 기술공부(스프링 시큐리티,스프링 웹 MVC,JPA 이론)
JPA 
* [JPA 영속성 컨텍스트](https://github.com/Jorados/sixWordsBoard/blob/main/JpaRecord/Persist.md)
* [엔티티 매핑](https://github.com/Jorados/sixWordsBoard/blob/main/JpaRecord/EntityMapping.md)
* [연관관계 매핑 기초 / 다양한 연관관계 매핑](https://github.com/Jorados/sixWordsBoard/blob/main/JpaRecord/association.md)
* [프록시와 연관관계 관리]()
* [값 타입]()
* [객체지향 쿼리 문법]()
