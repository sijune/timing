package com.sijune.timing.config.auth.dto;

import com.sijune.timing.domain.user.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable { //직렬화 기능

    private String name;
    private String email;
    private String picture;
    private String pushYn;
    private String pushCheckYn;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
        this.pushYn = user.getPushYn();
        this.pushCheckYn = user.getPushCheckYn();
    }

    public void setPushYn(String pushYn) {
        this.pushYn = pushYn;
    }
}
