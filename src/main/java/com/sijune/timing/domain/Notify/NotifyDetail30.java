package com.sijune.timing.domain.Notify;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //기본 생성자 생성
@Entity //테이블과 링크될 클래스
@Table(name="ST_TRADE_CAL30")
@IdClass(NotifyDetail30Id.class)
public class NotifyDetail30 {
    @Id
    @Column(name="analysis_date")
    private String analysisDate;

    @Id
    @Column(name="market_cd")
    private String marketCd;

    @Id
    @Column(name="stock_cd")
    private String stockCd;

    @Id
    @Column(name="stock_date")
    private String stockDate;

    @Column(name="close")
    private double close;

    @Column(name="ma20")
    private double ma20;

    @Column(name="upper")
    private double upper;

    @Column(name="lower")
    private double lower;

    @Column(name="pb")
    private double pb;

    @Column(name="iip21")
    private double iip21;

    @Builder
    public NotifyDetail30(String analysisDate, String marketCd, String stockCd, String stockDate, double close, double ma20, double upper, double lower, double pb, double iip21) {
        this.analysisDate = analysisDate;
        this.marketCd = marketCd;
        this.stockCd = stockCd;
        this.stockDate = stockDate;
        this.close = close;
        this.ma20 = ma20;
        this.upper = upper;
        this.lower = lower;
        this.pb = pb;
        this.iip21 = iip21;
    }
}
