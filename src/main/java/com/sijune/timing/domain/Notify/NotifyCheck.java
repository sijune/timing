package com.sijune.timing.domain.Notify;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //기본 생성자 생성
@Entity //테이블과 링크될 클래스
@Table(name="ST_NOTIFY_CHECK")
@IdClass(NotifyCheckId.class)
public class NotifyCheck {

    @Id
    @Column(name="email")
    private String email;

    @Id
    @Column(name="analysis_date")
    private String analysisDate;

    @Id
    @Column(name="market_loc_cd")
    private String marketLocCd;

    @Column(name="check_yn")
    private String checkYn;


    @Builder
    public NotifyCheck(String email, String analysisDate, String marketLocCd, String checkYn) {
        this.email = email;
        this.analysisDate = analysisDate;
        this.marketLocCd = marketLocCd;
        this.checkYn = checkYn;

    }

    public void updatePushCheck(String checkYn) {
        this.checkYn = checkYn;
    }

}

