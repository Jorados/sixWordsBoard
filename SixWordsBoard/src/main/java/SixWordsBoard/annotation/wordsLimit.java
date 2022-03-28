package SixWordsBoard.annotation;


import javax.validation.Constraint;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)   //어노테이션이 생성될 수 있는 위치를 지정하는 어노테이션 //enum 상수를 포함한 !멤버변수 선언 시!
@Retention(RetentionPolicy.RUNTIME) //어노테이션이 언제까지 유효할지 정하는 어노테이션 -> 실행동안은 계속 유효
@Constraint(validatedBy = sixWordsValidator.class)   //이걸 따름 (검증클래스)
public @interface wordsLimit {
    //커스텀 어노테이션 기본설정 3가지임
    String message() default "6개의 단어로 입력해주세요";
    Class[] groups() default {};  //설정안함
    Class[] payload() default {}; //설정안함
}
