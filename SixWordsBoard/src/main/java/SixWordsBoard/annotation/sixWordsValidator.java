package SixWordsBoard.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


//wordsLimit 어노테이션에 대한 검증 클래스
public class sixWordsValidator implements ConstraintValidator<wordsLimit, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context){
        //조건
        if(value == null){   //value값 아무것도없으면 그냥 false;
            return false;
        }
        value = value.strip(); //stip()을 사용하면 문자열의 맨앞과 맨뒤의 whitespace가 제거된다.
        String[] result = value.split(" ");  //split을 사용해서 문자를 " "기준으로 나눈다.

        if(result.length ==6)   //6문자면 true
            {
                return true;
            }else{
                return false;
        }
    }
}
