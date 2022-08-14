package com.ykteknolojistaj.restapi.service;

import builder.LogMessageBuilder;
import com.ykteknolojistaj.protointerface.Card;
import com.ykteknolojistaj.restapi.dao.CardDao;
import com.ykteknolojistaj.restapi.model.CardListModel;
import com.ykteknolojistaj.restapi.model.CardModel;
import lombok.Getter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardService {

    @Getter private CardListModel cardListModel;

    @Autowired
    private CardDao cardDao;

    private static final Logger LOG = LogManager.getLogger(CardService.class.getName());

    public List<CardModel> getCards(String customer_no, String uniqueID)
    {
        // initializing empty cardList
        cardListModel = new CardListModel();

        // logging the request to card limit database microservice
        LogMessageBuilder.Log(
                LOG, customer_no, uniqueID,
                this.getClass().getSimpleName(),
                this.getClass().getSimpleName() + " is called, now calling CardDao",
                "start",
                Level.INFO
        );

        // by using customer_no, getting all cards from the gRPC server (customer database microservice)
        // the return type is a list of protointerface.card models
        List<Card> cardModels = cardDao.receiveCards(customer_no, uniqueID);

        // Logging the incoming cards
        LogMessageBuilder.Log(
                LOG, customer_no, uniqueID,
                this.getClass().getSimpleName(),
                "Got a response from CardDao.",
                "end",
                Level.INFO
        );

        // copying all protointerface.card models to CardModel to use in response
        cardListModel.copyProtoCardArray(cardModels);

        return cardListModel.getCards();
    }

}
