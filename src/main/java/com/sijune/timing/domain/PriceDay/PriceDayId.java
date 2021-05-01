package com.sijune.timing.domain.PriceDay;

import java.io.Serializable;


public class PriceDayId implements Serializable {
    private String marketCd;
    private String stockCd;
    private String stockDate;

    public PriceDayId(){

    }

    public PriceDayId(String marketCd, String stockCd, String stockDate) {
        this.marketCd = marketCd;
        this.stockCd = stockCd;
        this.stockDate = stockDate;
    }
}