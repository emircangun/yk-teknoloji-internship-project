package com.ykteknolojistaj.restapi.dao;

import com.ykteknolojistaj.protointerface.Card;
import com.ykteknolojistaj.restapi.service.CardService;
import com.ykteknolojistaj.restapi.model.CardListModel;
import com.ykteknolojistaj.restapi.model.CardModel;
import com.ykteknolojistaj.restapi.model.LoggingMessage;
import lombok.Getter;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GetCardsDao {

    @Getter private CardListModel cardListModel;

    private static final Logger LOG = LogManager.getLogger(GetCardsDao.class.getName());

    public List<CardModel> getCards(CardService grpcLimitClient, String customer_no, String uniqueID)
    {
        // initializing empty cardList
        cardListModel = new CardListModel();

        // logging the request to card limit database microservice
        LoggingMessage loggingMessage = new LoggingMessage(customer_no, uniqueID, "GetCardsService is called, now calling CardClient", "CustomerLimitController", "start");
        LOG.log(Level.INFO, loggingMessage);

        // by using customer_no, getting all cards from the gRPC server (customer database microservice)
        // the return type is protointerface.card classz
        List<Card> cardModels = grpcLimitClient.receiveCards(customer_no, uniqueID);

        // Logging the response
        LoggingMessage loggingMessage2 = new LoggingMessage(customer_no, uniqueID, "Got a response from CardClient and the returned card(s) are: " + cardListModel.toString(), "CustomerLimitController", "end");
        LOG.log(Level.INFO, loggingMessage2);

        // copying all protointerface.card models to CardModel to use in response
        cardListModel.copyProtoCardArray(cardModels);

        return cardListModel.getCards();
    }

}
