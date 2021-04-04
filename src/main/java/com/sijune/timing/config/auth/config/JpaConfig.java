package com.sijune.timing.config.auth.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration //해당 어노테이션으로 스프링부트 컨텍스트가 해당 설정을 읽는다.
@EnableJpaAuditing // JPA Auditing 활성화
public class JpaConfig {
}
