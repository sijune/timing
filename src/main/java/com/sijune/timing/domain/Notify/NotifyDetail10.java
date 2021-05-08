package com.sijune.timing.domain.Notify;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //기본 생성자 생성
@Entity //테이블과 링크될 클래스
@Table(name="ST_TRADE_CAL10")
@IdClass(NotifyDetail10Id.class)
public class NotifyDetail10 {
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

    @Column(name="ema130")
    private double ema130;

    @Column(name="ema60")
    private double ema60;

    @Column(name="macd")
    private double macd;

    @Column(name="macd_signal")
    private double macdSignal;

    @Column(name="macd_hist")
    private double macdHist;

    @Column(name="fast_k")
    private double fastK;

    @Column(name="slow_d")
    private double slowD;

    @Builder
    public NotifyDetail10(String analysisDate, String marketCd, String stockCd, String stockDate, double close, double ema130, double ema60, double macd, double macdSignal, double macdHist, double fastK, double slowD) {
        this.analysisDate = analysisDate;
        this.marketCd = marketCd;
        this.stockCd = stockCd;
        this.stockDate = stockDate;
        this.close = close;
        this.ema130 = ema130;
        this.ema60 = ema60;
        this.macd = macd;
        this.macdSignal = macdSignal;
        this.macdHist = macdHist;
        this.fastK = fastK;
        this.slowD = slowD;
    }
}
