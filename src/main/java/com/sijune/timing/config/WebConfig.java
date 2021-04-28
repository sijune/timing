package com.sijune.timing.config;

import com.sijune.timing.config.auth.LoginUserArgumentResolver;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@RequiredArgsConstructor
@Configuration //해당 어노테이션으로 스프링부트 컨텍스트가 해당 설정을 읽는다.
public class WebConfig implements WebMvcConfigurer {
    //WebMvcConfigurer 인터페이스 - @EnableWebMvc로 자동 생성된 빈의 설정 값을 추가한다.
    private final LoginUserArgumentResolver loginUserArgumentResolver;

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
        //파라미터 리저브를 추가한다.
        argumentResolvers.add(loginUserArgumentResolver);
    }
}