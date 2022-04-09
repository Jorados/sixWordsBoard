### 테스트 코드 작성
---------------------------------------------------
테스트 코드를 작성하는 이유는 내가 작성한 Repository와 Service가 제대로 개발 되었는지를 알기 위함이다.       
스프링 부트에서는 에플리케이션TEST모드가 있기 때문에 이것을 활용하면 된다.
간단하게 회원가입 / (회원)조회 / 동일아이디 예외 등 의 테스트 코드 작성을 통해서 개발한 코드를 확인해보겠다.

![image](https://user-images.githubusercontent.com/100845256/161965211-1d471f92-101d-443c-8d0f-48841ac9d5fe.png)

#### @Runwith
   *  @Runwith를 사용하는 이유 그냥 @SpringBootTest를 사용하면 application context를 전부 로딩해서 자칫 잘못하면 무거운 프로젝트가될수있다.
하지만 @Runwith(SprintRunner.class)를 사용하면  @Autowired,@Bean에 해당되는것들에만 application context를 로딩하게 되므로 사용한다.

#### @Autowired
   *  @Autowired를 사용하면 실제 application에서 객체를 끌어오는거라고 보면된다.
   
#### 회원가입
![image](https://user-images.githubusercontent.com/100845256/161965918-f2be70d1-f23c-4299-a8af-a89814f28b88.png)

맴버객체 변수들의 값을 지정해준다. 그 후에 memberService에서 만들었던 join기능을 적용하고 마지막으로 Assertions.assertEquals를 통해서 repository에 있는 member객체와 회원가입할 때 사용한 아이디를 repository에서 만들었던 findById를 통해서 DB의 정보와 입력한 정보가 일치한지 확인한다.

#### 조회
![image](https://user-images.githubusercontent.com/100845256/161966566-55c30331-12f4-4b94-b60c-a86dbcce06bc.png)

getMember라는 메서드를 미리 만들어서 메인메서드에서 편하게 member 생성하고 memberService로 가입하고 가입한 아이디를 repository의 findById를 통해서 찾는다. 

#### 동일아이디예외
![image](https://user-images.githubusercontent.com/100845256/161966967-143ec44c-cb4b-449f-becd-a827116409ec.png)

객체명은 다르지만 같은 MemberId로 가입하게되면 예외처리가 되는지 확인한다. MemberService의 UniqueLoginId 동작 확인이다.

### 이로써 테스트 코드를 통해서 내가 만들 Repository와 Service가 제대로 개발된 것을 확인 할 수 있다. JAVA와 JPA를 통해서 테이터필드와 테이터테이블과 연관관계를 만들고 Repository와 Service를 개발해서 회원과 게시물 관련 개발이 끝났다. 컨트롤러와 프론트페이지를 만들면 끝이다.
