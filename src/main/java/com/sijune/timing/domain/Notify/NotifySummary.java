package com.sijune.timing.domain.Notify;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //기본 생성자 생성
@Entity //테이블과 링크될 클래스
@Table(name="ST_NOTIFY_SUMMARY")
@IdClass(NotifySummaryId.class)
public class NotifySummary {

    @Id
    @Column(name="analysis_date")
    private String analysisDate;

    @Id
    @Column(name="trade_cls_cd")
    private String tradeClsCd;

    @Id
    @Column(name="market_loc_cd")
    private String marketLocCd;

    @Column(name="buy_count")
    private int buyCount;

    @Column(name="sell_count")
    private int sellCount;

    @Builder
    public NotifySummary(String analysisDate, String tradeClsCd, String marketLocCd, int buyCount, int sellCount) {
        this.analysisDate = analysisDate;
        this.tradeClsCd = tradeClsCd;
        this.marketLocCd = marketLocCd;
        this.buyCount = buyCount;
        this.sellCount = sellCount;
    }

}

