package com.sijune.timing.domain.Notify;

import java.io.Serializable;

public class NotifyDetail20Id implements Serializable {
    private String analysisDate;
    private String marketCd;
    private String stockCd;
    private String stockDate;

    public NotifyDetail20Id(){

    }

    public NotifyDetail20Id(String analysisDate, String marketCd, String stockCd, String stockDate) {
        this.analysisDate = analysisDate;
        this.marketCd = marketCd;
        this.stockCd = stockCd;
        this.stockDate = stockDate;
    }
}