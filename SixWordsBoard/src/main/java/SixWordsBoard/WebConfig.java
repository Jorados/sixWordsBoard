package SixWordsBoard;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginCheckInterceptor()) //session방식 인증체크 //좀있다가
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/", "/member/new", "/member/afternew/{memberName}", "/member/login", "/board",
                        "/css/**", "/*.ico", "/error");  //인터셉터를 제외할 url 패턴을 등록하는 메서드
    }
}
