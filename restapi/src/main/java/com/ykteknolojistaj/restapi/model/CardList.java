package com.ykteknolojistaj.restapi.model;
import java.util.ArrayList;
import java.util.List;

    public class CardList {
        private final List<CardModel> cards;
        public CardList(List<com.ykteknolojistaj.protointerface.Card> protoCards)
        {
            cards = new ArrayList<>();
            for (int i = 0; i < protoCards.size(); i++)
            {
                String currentCardNo = protoCards.get(i).getCardNo();
                double currentLimit = protoCards.get(i).getLimit();
                cards.add(new CardModel(currentCardNo, currentLimit));
            }
        }
        public List<CardModel> getCards()
        {
            return cards;
        }
    }


