package com.sijune.timing.domain.Notify;

import java.io.Serializable;

public class NotifySummaryId implements Serializable {
    private String analysisDate;
    private String tradeClsCd;
    private String marketLocCd;

    public NotifySummaryId(){

    }

    public NotifySummaryId(String analysisDate, String tradeClsCd, String marketLocCd) {
        this.analysisDate = analysisDate;
        this.tradeClsCd = tradeClsCd;
        this.marketLocCd = marketLocCd;
    }
}