package com.sijune.timing.config.auth;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) //파라미터에만 어노테이션 사용 가능
@Retention(RetentionPolicy.RUNTIME) //컴파일 이후 자바JVM에서 참조가능
public @interface LoginUser { //어노테이션으로 사용하겠다.
}
