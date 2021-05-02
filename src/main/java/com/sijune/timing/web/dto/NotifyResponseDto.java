package com.sijune.timing.web.dto;

import com.sijune.timing.domain.Notify.Notify;
import lombok.Getter;

import java.util.List;

@Getter
public class NotifyResponseDto {

    private String analysisDate;
    private String marketLocCd;
    private String marketCd;
    private String stockCd;
    private String stockNm;
    private String stockDate;
    private String opinion;

    public NotifyResponseDto() {
    }


    public NotifyResponseDto(String analysisDate, String marketLocCd, String marketCd, String stockCd, String stockNm, String stockDate, String opinion) {
        this.analysisDate = analysisDate;
        this.marketLocCd = marketLocCd;
        this.marketCd = marketCd;
        this.stockCd = stockCd;
        this.stockNm = stockNm;
        this.stockDate = stockDate;
        this.opinion = opinion;

    }
}
