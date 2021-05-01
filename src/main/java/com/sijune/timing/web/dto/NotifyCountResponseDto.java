package com.sijune.timing.web.dto;

import lombok.Getter;

@Getter
public class NotifyCountResponseDto {

    private String analysisDate;
    private String positionBuy;
    private int countBuy;
    private String positionSell;
    private int countSell;

    public NotifyCountResponseDto(String analysisDate, String positionBuy, int countBuy, String positionSell, int countSell) {
        this.analysisDate = analysisDate;
        this.positionBuy = positionBuy;
        this.countBuy = countBuy;
        this.positionSell = positionSell;
        this.countSell = countSell;
    }
}
