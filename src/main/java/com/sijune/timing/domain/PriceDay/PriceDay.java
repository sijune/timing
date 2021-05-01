package com.sijune.timing.domain.PriceDay;

import com.sijune.timing.domain.Notify.NotifyId;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;


@Getter
@NoArgsConstructor //기본 생성자 생성
@Entity //테이블과 링크될 클래스
@Table(name="ST_PRICE_DAY")
@IdClass(PriceDayId.class)
public class PriceDay {

    @Id
    @Column(name="market_cd")
    private String marketCd;

    @Id
    @Column(name="stock_cd")
    private String stockCd;

    @Id
    @Column(name="stock_date")
    private String stockDate;

    @Column(name="open")
    private BigDecimal open;

    @Column(name="high")
    private BigDecimal high;

    @Column(name="low")
    private BigDecimal low;

    @Column(name="close")
    private BigDecimal close;

    @Column(name="diff")
    private BigDecimal diff;

    @Column(name="volume")
    private BigDecimal volume;


    @Builder
    public PriceDay(String marketCd, String stockCd, String stockDate, BigDecimal open, BigDecimal high, BigDecimal low, BigDecimal close, BigDecimal diff, BigDecimal volume) {
        this.marketCd = marketCd;
        this.stockCd = stockCd;
        this.stockDate = stockDate;
        this.open = open;
        this.high = high;
        this.low = low;
        this.close = close;
        this.diff = diff;
        this.volume = volume;

    }


}
