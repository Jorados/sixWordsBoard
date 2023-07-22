### :clipboard: sixWordBoard    
---
        
1. 프로젝트     
    + 스프링부트와 jpa를 활용한 웹 애플리케이션 게시판(헤밍웨이가 6단어만으로 쓴 소설처럼 6단어로만 쓸 수 있는 게시판)

2. 개요
   + 명칭 : sixWordBoard
   + 개발자 : 조성진
   + 개발기간 : 2022-03-19 ~ 2022-06-20      
   + 사용언어 : JAVA(백) / html(thymeleaf),부트스트랩(프론트)(SSR)  
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

----------
### ⭐ 부분 개발 정리
* [1주차~2주차 / 환경설정 및 엔티티설계](https://github.com/Jorados/sixWordsBoard/blob/main/record/fisrt.md)
* [3~4주차 / 레포지토리 및 서비스개발](https://github.com/Jorados/sixWordsBoard/blob/main/record/repository_service.md)
* [5~6주차 / 엔티티 데이터전송을 위한 dto개발 및 6글자 제한을 두는 어노테이션 개발](https://github.com/Jorados/sixWordsBoard/blob/main/record/dto_annotation.md)
* [6~7주차 / 테스트 코드 작성](https://github.com/Jorados/sixWordsBoard/blob/main/record/test.md)
* [8~9주차 / 웹 컨트롤러(스프링 웹mvc) 개발](https://github.com/Jorados/sixWordsBoard/blob/main/record/webController.md)
* [10~11주차 / 웹 페이지(프론트) 개발](https://github.com/Jorados/sixWordsBoard/blob/main/record/webPage.md)
* [12~13주차 / 웹 서버 배포 ](https://github.com/Jorados/sixWordsBoard/blob/main/record/server.md)
* [14주차 ~ 마지막 주차 / 결과](https://sixwordsboard2.herokuapp.com/)    

--------
### 💥메인화면   
![image](https://user-images.githubusercontent.com/100845256/216206336-79ce294a-5378-447f-94a1-7f6da69fb836.png)
