### sixWordBoard
---

1. 프로젝트
    + 스프링부트와 jpa를 활용한 게시판(헤밍웨이가 6단어만으로 쓴 소설처럼 6단어로만 쓸 수 있는 게시판)
    + [참고 사이트](http://www.sixwordswriter.cf/)

2. 개요
   + 명칭 : sixWordBoard
   + 개발자 : 조성진
   + 개발기간 : 2022-03-19 ~ ing
   + 사용언어 : JAVA 11(백) / js,html,css,부트스트랩(프론트)
   + 개발환경 : SpringBoot 2.6.4 / jpa / H2 database(경량db,관계형데이터베이스RDBMS)

3. 구현기능
   + 스프링 시큐리티를 이용한 JWT토큰방식의 로그인,회원가입 및 소셜로그인(카카오톡,구글)
   + 기본적인 게시글 crud(생성,조회,수정,삭제)기능
   + 설계한 도메인과 SpringBoot,JPA를 기반으로 한 기본적인 crud restful api를 직접 만들기
   + import(아임포트) api를 이용한 가상간편 결제 시스템(카카오페이,페이코) --> 게시물 좋아요 할때마다?
   + websoket을 활용한 게시물 좋아요 달리면 알림기능
   + 구글 smtp를 이용하여 회원가입 시 이메일 인증 기능 구현을 통한 회원가입 
 
4. 서버환경
   + vmware + Ubuntu(LINUX의 버전) / AWS EC2(가상서버) + Ubuntu(LINUX의 버전)
   + docker를 이용한 클라이언트,서버,데이터베이스 관리
   + nginx 웹서버를 이용하여 다른 사람들이 내 pc에 있는 웹브라우저에 접속을 가능하게함

5. 개발방향
   + 게시판과 기능들을 먼저 구현하고 서버환경을 설정하는 방향으로 진행하겠습니다.
   + 엔티티설계(domain) -> repository설계 -> service설계 -> webController설계 -> 프론트설계 -> 이후 추가 기능들 설계 -> api설계
