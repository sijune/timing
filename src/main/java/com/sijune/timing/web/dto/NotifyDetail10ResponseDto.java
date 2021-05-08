package com.sijune.timing.web.dto;

import com.sijune.timing.domain.Notify.NotifyDetail10;
import com.sijune.timing.domain.posts.Posts;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
public class NotifyDetail10ResponseDto {

    private String analysisDate;
    private String marketCd;
    private String stockCd;
    private String stockDate;
    private double close;
    private double ema130;
    private double ema60;
    private double macd;
    private double macdSignal;
    private double macdHist;
    private double fastK;
    private double slowD;

    public NotifyDetail10ResponseDto(NotifyDetail10 entity) {
        this.analysisDate = entity.getAnalysisDate();
        this.marketCd = entity.getMarketCd();
        this.stockCd = entity.getStockCd();
        this.stockDate = entity.getStockDate();
        this.close = entity.getClose();
        this.ema130 = entity.getEma130();
        this.ema60 = entity.getEma60();
        this.macd = entity.getMacd();
        this.macdSignal = entity.getMacdSignal();
        this.macdHist = entity.getMacdHist();
        this.fastK = entity.getFastK();
        this.slowD = entity.getSlowD();
    }

}
