### 4주차 기록
----------------------
#### DTO 개발                                           

dto란 무엇인가? --> 데이터 전송 객체이다. --> 어디에서 어디로 전송하는가...?                          
dto는 웹 서비스의 클라이언트와 서버, 더 자세히는 클라이언트와 서버의 서비스 계층 사이에서 교환되는 데이터를 담는 그릇이라고 할 수 있다.                     
데이터를 전송할 때 필요하다. @PostMapping

@NotBlank는 전송하는 데이터의 null값과 ""," "를 모두 허용하지 않는다는 어노테이션이다.
@NotNull @NotEmpty 과 같은 어노테이션들 보다 가장 강력한 어노테이션이다.

![image](https://user-images.githubusercontent.com/100845256/161749240-e06e135d-029f-4003-9cc2-bc66ab8c5ef5.png)

![image](https://user-images.githubusercontent.com/100845256/161749308-5d988eb0-99d7-4eaa-97a5-ff7da21c07a5.png)

![image](https://user-images.githubusercontent.com/100845256/161749343-d9c90bef-734f-41ed-a593-19c79c13fa48.png)

![image](https://user-images.githubusercontent.com/100845256/161749379-0b7f420a-5ab2-4cbb-bc46-0e85c1122d24.png)

--------------------------------

#### 6단어 제한 어노테이션 개발

@NotNull , @NotEmpty , @NotBlank 등 스프링에서 제공하는 유효성 검사 어노테이션들은 나에게 필요한 6단어 검증에 관련된 기능을 제공하지않는다. 그렇기 때문에 직접 개발을 진행해야한다.        
javax에서 제공되는 ContraintValidator 라는 인터페이스를 구현하는 검증 클래스 객체를 만들어 isValid() 메서드를 오버라이딩하여 split()과 strip()을 이용하여 6단어 판별을 진행한다.          
이를 통해 만들어지는 커스텀 어노테이션은 게시글 작성 요청 시 DTO 객체에 사용하여 스프링 인터셉터에서 6단어 유효성을 검증한다.      
 
wordsLimit(annotation)    
![image](https://user-images.githubusercontent.com/100845256/161750306-a032916b-c54f-46ff-a1a1-d61b763a5350.png)

sixWordsValidator.java   
![image](https://user-images.githubusercontent.com/100845256/161750530-bf3e2036-f226-4322-9991-3617e694a7b8.png)

