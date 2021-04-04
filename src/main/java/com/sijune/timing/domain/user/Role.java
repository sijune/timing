package com.sijune.timing.domain.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Role {

    GUSET("ROLE_GUEST", "손님"), //스프링 시큐리티에서 ROLE_ 이 필요
    USER("ROLE_USER", "일반 사용자");

    private final String key;
    private final String title;
}