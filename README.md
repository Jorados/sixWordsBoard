### sixWordBoard
---

1. 프로젝트
    + 스프링부트와 jpa를 활용한 웹 애플리케이션 게시판(헤밍웨이가 6단어만으로 쓴 소설처럼 6단어로만 쓸 수 있는 게시판)
    + [참고 사이트](http://www.sixwordswriter.cf/)

2. 개요
   + 명칭 : sixWordBoard
   + 개발자 : 조성진
   + 개발기간 : 2022-03-19 ~ ing
   + 사용언어 : JAVA 11(백) / js,html(thymeleaf),css,부트스트랩(프론트)
   + 개발환경 : SpringBoot 2.6.4 / jpa / Mysql(관계형데이터베이스RDBMS)

3. 구현기능
   + 기본적인 게시글 crud(생성,조회,수정,삭제)기능
   + (핵심)스프링 시큐리티를 이용한 JWT토큰방식의 로그인,회원가입 및 소셜로그인(카카오톡,구글)
   + (핵심)설계한 도메인과 SpringBoot,JPA를 기반으로 한 기본적인 crud restful api를 직접 만들기
   + import(아임포트) api를 이용한 가상간편 결제 시스템(카카오페이,페이코) --> 게시물 좋아요 할때마다?
   + websoket을 활용한 게시물 좋아요 달리면 알림기능
   + 구글 smtp를 이용하여 회원가입 시 이메일 인증 기능 구현을 통한 회원가입 
 
4. 서버환경
   + AWS EC2(가상서버) + Ubuntu(LINUX의 버전) 에 배포


5. 개발방향
   + 게시판과 기능들을 먼저 구현하고 서버환경을 설정하는 방향으로 진행하겠습니다.
   + 엔티티개발(domain) -> repository개발(기능) -> service개발(기능구현) -> webController개발 -> 프론트개발 -> 기본 게시판 개발 -> 추가 기능 개발 -> 배포

----------
### 부분 개발 정리
* [1주차~2주차 / 환경설정 및 엔티티설계](https://github.com/Jorados/sixWordsBoard/blob/main/record/fisrt.md)
* [3주차 / 레포지토리 및 서비스개발](https://github.com/Jorados/sixWordsBoard/blob/main/record/repository_service.md)
* [4주차 / 엔티티 데이터전송을 위한 dto개발 및 6글자 제한을 두는 어노테이션 개발](https://github.com/Jorados/sixWordsBoard/blob/main/record/repository_service.md)
* [5주차~6주차 / 웹 컨트롤러(스프링 웹mvc) 개발](https://github.com/Jorados/sixWordsBoard/blob/main/record/webController.md)
* [7주차 / 웹 페이지(프론트) 개발](https://github.com/Jorados/sixWordsBoard/blob/main/record/webPage.md)

---------
### 스프링 기술공부(스프링 시큐리티,스프링 웹 MVC,JPA 이론)
JPA 공부
* [JPA 영속성 컨텍스트](https://github.com/Jorados/sixWordsBoard/blob/main/JpaRecord/Persist.md)
* [엔티티 매핑](https://github.com/Jorados/sixWordsBoard/blob/main/JpaRecord/EntityMapping.md)
* [연관관계 매핑 기초 / 다양한 연관관계 매핑]()
* [프록시와 연관관계 관리]()
* [값 타입]()
* [객체지향 쿼리 문법]()
  
spring MVC
* [서블릿]()
* [서블릿,JSP,MVC 패턴]()
* [MVC프레임워크 스프링 MVC-구조이해]()
* [스프링 MVC-기본기능]()
* [간단한 페이지 만들기]()
* [타임리프-기본기능]()
* [타임리프-스프링 통합과 폼]()
* [메시지,국제화]()
* [검증1]()
* [검증2]()
* [로그인처리1-쿠키,세션]()
* [로그인 처리2-필터,인터셉터]()
* [예외처리와 오류페이지]()
* [API 예외 처리]()
* [스프링 타입 컨버터]()
* [파일업로드]()

spring security
* [이론]()
