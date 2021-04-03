package com.sijune.timing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication //스프링 부트 설정을 여기부터 읽기 시작
public class Application {
    public static void main(String[] args) { //내장 WAS 구동
        SpringApplication.run(Application.class, args);
    }
}