package com.sijune.timing.web.dto;

import com.sijune.timing.domain.Notify.NotifyDetail20;
import com.sijune.timing.domain.Notify.NotifyDetail30;
import lombok.Getter;

@Getter
public class NotifyDetail30ResponseDto {

    private String analysisDate;
    private String marketCd;
    private String stockCd;
    private String stockDate;
    private double close;
    private double ma20;
    private double upper;
    private double lower;
    private double pb;
    private double iip21;

    public NotifyDetail30ResponseDto(NotifyDetail30 entity) {
        this.analysisDate = entity.getAnalysisDate();
        this.marketCd = entity.getMarketCd();
        this.stockCd = entity.getStockCd();
        this.stockDate = entity.getStockDate();
        this.close = entity.getClose();
        this.ma20 = entity.getMa20();
        this.upper = entity.getUpper();
        this.lower = entity.getLower();
        this.pb = entity.getPb();
        this.iip21 = entity.getIip21();
    }

}
