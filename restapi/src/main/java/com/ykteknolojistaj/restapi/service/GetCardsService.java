package com.ykteknolojistaj.restapi.service;

import com.ykteknolojistaj.protointerface.Card;
import com.ykteknolojistaj.restapi.controller.CustomerLimitController;
import com.ykteknolojistaj.restapi.grpcClient.CardClient;
import com.ykteknolojistaj.restapi.model.CardList;
import com.ykteknolojistaj.restapi.model.CardModel;
import com.ykteknolojistaj.restapi.model.LoggingMessage;
import lombok.Getter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;

public class GetCardsService {

    @Getter private CardList cardList;

    private static final Logger LOG = LogManager.getLogger(CustomerLimitController.class.getName());

    public List<CardModel> getCards(CardClient grpcLimitClient, String customer_no, String uniqueID)
    {
        // initializing empty cardList
        cardList = new CardList();

        // by using customer_no, getting all cards from the gRPC server (customer database microservice)
        // the return type is protointerface.card classz
        List<Card> cardModels = grpcLimitClient.receiveCards(customer_no, uniqueID);

        // copying all protointerface.card models to CardModel to use in response
        cardList.copyProtoCardArray(cardModels);

        // Loggging the response
        LoggingMessage loggingMessage2 = new LoggingMessage(customer_no, uniqueID, "Got a response from CardClient and the returned card(s) are: " + cardList.toString(), "CustomerLimitController", "end");
        String logMessage = loggingMessage2.toString();
        LOG.log(Level.INFO, logMessage);

        return cardList.getCards();
    }

}
