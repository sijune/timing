package com.sijune.timing.domain.user;

import com.sijune.timing.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table(name="USER")
public class User extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String email;

    @Column
    private String picture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name="push_yn")
    private String pushYn;

    @Column(name="push_count")
    private int pushCount;

    @Column(name="subscribe_period")
    private int subscribePeriod;

    @Builder
    public User(String name, String email, String picture, Role role, String pushYn, int pushCount, int subscribePeriod) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
        this.pushYn = pushYn;
        this.pushCount = pushCount;
        this.subscribePeriod = subscribePeriod;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;

        return this;
    }

    public User update_push_yn(String pushYn) {
        this.pushYn = pushYn;

        return this;
    }

    public String getRoleKey() {
        return this.role.getKey();
    }

    public String getPushYn() {
        return this.pushYn;
    }
}
