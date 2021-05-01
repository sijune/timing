package com.sijune.timing.domain.Notify;

import java.io.Serializable;

public class NotifyId implements Serializable {
    private String analysisDate;
    private String marketCd;
    private String stockCd;
    private String stockDate;

    public NotifyId(){

    }

    public NotifyId(String analysisDate, String marketCd, String stockCd, String stockDate) {
        this.analysisDate = analysisDate;
        this.marketCd = marketCd;
        this.stockCd = stockCd;
        this.stockDate = stockDate;
    }
}