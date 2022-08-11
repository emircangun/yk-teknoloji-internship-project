package com.ykteknolojistaj.restapi.model;

public class CardModel {
    private final String card_no;
    private final double limit;

    public CardModel(String currentCardNo, double currentLimit) {
        this.card_no = currentCardNo;
        this.limit = currentLimit;
    }

    public double getLimit() {
        return limit;
    }

    public String getCard_no() {
        return card_no;
    }

    /**
     * Converting the card to String.
     * @return serialized Card
     */
    @Override
    public String toString() {
        return "CardNo:" + card_no + ",CardLimit:" + limit;
    }

}
