package com.sijune.timing.web.dto;

import com.sijune.timing.domain.Notify.Notify;
import lombok.Getter;

import java.util.List;

@Getter
public class NotifyResponseDto {

    private String analysisDate;
    private String marketCd;
    private String stockCd;
    private String stock_date;
    private String opinion;

    public NotifyResponseDto() {
    }

    public NotifyResponseDto(Notify entity) {
        this.analysisDate = entity.getAnalysisDate();
        this.marketCd = entity.getMarketCd();
        this.stockCd = entity.getStockCd();
        this.stock_date = entity.getStockDate();
        if(entity.getBuy() == 1) {
            this.opinion = "매수";
        }else if(entity.getBuy()==2){
            this.opinion = "강한매수";
        }else if(entity.getSell()== 1){
            this.opinion = "매도";
        }else if(entity.getSell() == 2){
            this.opinion = "강한매도";
        }else{
            this.opinion = "의견없음";
        }


    }
}
