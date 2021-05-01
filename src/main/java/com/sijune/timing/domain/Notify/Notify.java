package com.sijune.timing.domain.Notify;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor //기본 생성자 생성
@Entity //테이블과 링크될 클래스
@Table(name="ST_NOTIFY")
@IdClass(NotifyId.class)
public class Notify {

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

    @Column(name="trade_cls_cd")
    private String tradeClsCd;

    @Column(name="sell")
    private int sell;

    @Column(name="buy")
    private int buy;

    @Builder
    public Notify(String analysisDate, String marketCd, String stockCd, String stockDate, String tradeClsCd, int sell, int buy) {
        this.analysisDate = analysisDate;
        this.marketCd = marketCd;
        this.stockCd = stockCd;
        this.stockDate = stockDate;
        this.tradeClsCd = tradeClsCd;
        this.sell = sell;
        this.buy = buy;

    }

}

