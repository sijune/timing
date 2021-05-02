package com.sijune.timing.domain.Notify;

import java.io.Serializable;

public class NotifyCheckId implements Serializable {
    private String email;
    private String analysisDate;
    private String marketLocCd;

    public NotifyCheckId(){

    }

    public NotifyCheckId(String email, String analysisDate, String marketLocCd, String checkYn) {
        this.email = email;
        this.analysisDate = analysisDate;
        this.marketLocCd = marketLocCd;
    }

}