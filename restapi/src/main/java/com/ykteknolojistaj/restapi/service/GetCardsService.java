package com.ykteknolojistaj.restapi.service;

import com.ykteknolojistaj.protointerface.Card;
import com.ykteknolojistaj.restapi.grpcClient.CardLimitClient;
import com.ykteknolojistaj.restapi.model.CardList;
import com.ykteknolojistaj.restapi.model.CardModel;
import com.ykteknolojistaj.restapi.model.LoggingMessage;
import lombok.Getter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCardsService {

    @Getter private CardList cardList;

    private static final Logger LOG = LogManager.getLogger(GetCardsService.class.getName());

    public List<CardModel> getCards(CardLimitClient grpcLimitClient, String customer_no, String uniqueID)
    {
        // initializing empty cardList
        cardList = new CardList();

        // logging the request to card limit database microservice
        LoggingMessage loggingMessage = new LoggingMessage(customer_no, uniqueID, "GetCardsService is called, now calling CardClient", "CustomerLimitController", "start");
        LOG.log(Level.INFO, loggingMessage);

        // by using customer_no, getting all cards from the gRPC server (customer database microservice)
        // the return type is protointerface.card classz
        List<Card> cardModels = grpcLimitClient.receiveCards(customer_no, uniqueID);

        // Logging the response
        LoggingMessage loggingMessage2 = new LoggingMessage(customer_no, uniqueID, "Got a response from CardClient and the returned card(s) are: " + cardList.toString(), "CustomerLimitController", "end");
        LOG.log(Level.INFO, loggingMessage2);

        // copying all protointerface.card models to CardModel to use in response
        cardList.copyProtoCardArray(cardModels);

        return cardList.getCards();
    }

}
