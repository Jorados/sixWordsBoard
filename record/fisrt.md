### 1주차 기록
---
spring 프로젝트를 만들고 사용 할 라이브러리를 전부 설정해줬습니다. 속성 설정도 해줬습니다. java11 spring jpa 구현체hibernate 이렇게 다 연결해서 셋팅완료.


1.속성 설정: mysql 기본 셋팅 등등

![properties설정](https://user-images.githubusercontent.com/100845256/159674519-e9473645-77e0-42c8-a7dd-ba57535e3476.PNG)

2.사용 할 라이브러리 설정

![gradle(라이브러린 의존관계)설정](https://user-images.githubusercontent.com/100845256/159674544-22fa5516-9cc0-40b8-a386-10b2788dded6.PNG)

3.spring jpa를 활용한 게시판 기본 엔티티(본체) 코딩 -> 데이터테이블 구조와 연관관계 매핑 / myslq workbench에서 다이어그램화(아직 초반이라 기본만 있음)
기본엔티티는 이렇게 가져가고 기능 구현함에 있어서 점점 추가 할 예정

![SixWordsBoard db 다이어그램](https://user-images.githubusercontent.com/100845256/159674552-7540c1fe-4b3c-409e-9eaf-2b01501d8cb0.PNG)
![SixWordsBoard db테이블](https://user-images.githubusercontent.com/100845256/159674560-f40d0f80-06ea-42e3-bd1e-65de306bc27d.PNG)

Member테이블의 PK(Id):테이터베이스의 메인 키 --> member_id /  FK:해당 데이터베이스의 접근 키 --> x 없음(@JoinColumn을 통해서 설정)
Board테이블의 PK(Id):테이터베이스의 메인 키 --> board_id /  FK:해당 데이터베이스의 접근 키 --> member_id (@JoinColumn을 통해서 설정)
Likes테이블의 PK(Id):테이터베이스의 메인 키 --> likes_id /  FK:해당 데이터베이스의 접근 키 --> member_id,board_id (@JoinColumn을 통해서 설정)              

스프링 JPA를 통한 연관관계를 설정하여 본인이 원하는 구조의 RDBMS데이터베이스의 테이블을 설계할 수 있습니다.
저는 게시물에 필요한  Member,Board,Likes 를 기본 데이터테이블로 설계하고 그에 맞는 연관관계를 코딩했습니다.

Member
![Member 엔티티](https://user-images.githubusercontent.com/100845256/161729222-27064a74-b1fb-4e0d-8d7d-6ed498239208.PNG)

Likes
  ![Likes엔티티](https://user-images.githubusercontent.com/100845256/161729455-cf7ef41a-8d08-4561-a712-f2ea828391a0.PNG)

Board
  ![Board엔티티](https://user-images.githubusercontent.com/100845256/161729450-bbf070cc-3665-4b54-83ef-c64037036298.PNG)

