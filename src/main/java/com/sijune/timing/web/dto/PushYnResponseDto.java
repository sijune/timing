package com.sijune.timing.web.dto;

import com.sijune.timing.domain.user.User;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter //선언된 모든 필드에 getter 생성
@RequiredArgsConstructor //final 이 있는 변수 생성자 생성
public class PushYnResponseDto {
    private final User user;

}