package com.ykteknolojistaj.restapi.model;

import java.util.ArrayList;
import java.util.List;

public class CardListModel {

    // cards list consists of java card model
    private final List<CardModel> cards;

    /**
     * Default constructor, initializing cards list as empty
     */
    public CardListModel()
    {
        cards = new ArrayList<>();
    }

    /**
     * Copies from list of proto cards into java card model array
     * @param protoCards list of proto cards
     */
    public void copyProtoCardArray(List<com.ykteknolojistaj.protointerface.Card> protoCards)
    {
        for (com.ykteknolojistaj.protointerface.Card protoCard : protoCards) {
            String currentCardNo = protoCard.getCardNo();
            double currentLimit = protoCard.getLimit();
            cards.add(new CardModel(currentCardNo, currentLimit));
        }
    }

    /**
     * @return the list of CardModel
     */
    public List<CardModel> getCards() {
        return cards;
    }

    /**
     * @return size of the list
     */
    public int size() {
        return cards.size();
    }

    /**
     * Converting each card in the list to String.
     * @return serialized list as String
     */
    @Override
    public String toString() {
        StringBuilder temp = new StringBuilder();

        for (CardModel card : cards) {
            temp.append(card.toString());
        }

        return temp.toString();
    }

}


