package com.sijune.timing.domain.Common;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@NoArgsConstructor //기본 생성자 생성
@Entity //테이블과 링크될 클래스
@Table(name="ST_LIST")
@IdClass(StockId.class)
public class Stock {
    @Id
    @Column(name="market_cd")
    private String marketCd;

    @Id
    @Column(name="stock_cd")
    private String stockCd;


    @Column(name="stock_nm")
    private String stockNm;

    @Builder
    public Stock(String marketCd, String stockCd, String stockNm) {
        this.marketCd = marketCd;
        this.stockCd = stockCd;
        this.stockNm = stockNm;

    }

}