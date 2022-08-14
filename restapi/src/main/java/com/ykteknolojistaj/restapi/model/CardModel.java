package com.ykteknolojistaj.restapi.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CardModel {
    @NotBlank
    @Size(min = 10, max = 10)
    private final String card_no;

    @NotBlank
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
        return "CardNo: " + card_no + ", CardLimit:" + limit;
    }

}
