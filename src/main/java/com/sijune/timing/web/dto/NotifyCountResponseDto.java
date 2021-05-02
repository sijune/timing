package com.sijune.timing.web.dto;

import lombok.Getter;

@Getter
public class NotifyCountResponseDto {

    private String analysisDate;
    private String marketLocCd;
    private String positionBuy;
    private int countBuy;
    private String positionSell;
    private int countSell;
    private String pushCheck;

    public NotifyCountResponseDto(String analysisDate, String marketLocCd, String positionBuy, int countBuy, String positionSell, int countSell, String pushCheck) {
        this.analysisDate = analysisDate;
        this.marketLocCd = marketLocCd;
        this.positionBuy = positionBuy;
        this.countBuy = countBuy;
        this.positionSell = positionSell;
        this.countSell = countSell;
        this.pushCheck = pushCheck;
    }
}
