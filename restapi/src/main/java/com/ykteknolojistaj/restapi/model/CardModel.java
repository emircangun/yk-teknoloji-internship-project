package com.ykteknolojistaj.restapi.model;

public class CardModel {
    private String card_no;
    private double limit;

    public CardModel(String currentCardNo, double currentLimit) {
        this.card_no=currentCardNo;
        this.limit=currentLimit;
    }
    public double getLimit() {

        return limit;

    }
    public String getCard_no() {

        return card_no;

    }
}
