package com.sijune.timing.domain.Common;

import java.io.Serializable;

public class StockId implements Serializable {
    private String marketCd;
    private String stockCd;

    public StockId(){

    }

    public StockId(String marketCd, String stockCd, String stockNm) {
        this.marketCd = marketCd;
        this.stockCd = stockCd;
    }
}