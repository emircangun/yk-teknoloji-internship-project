package com.ykteknolojistaj.restapi.CardGrpcClient;


import com.ykteknolojistaj.protointerface.Card;
import com.ykteknolojistaj.protointerface.CardRequest;
import com.ykteknolojistaj.protointerface.CardResponse;
import com.ykteknolojistaj.protointerface.CardServiceGrpc;
import com.ykteknolojistaj.restapi.controller.CustomerLimitController;
import com.ykteknolojistaj.restapi.model.LoggingMessage;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardClient {

    @GrpcClient("cardService")
    private CardServiceGrpc.CardServiceBlockingStub cardServiceStub;

    private static final Logger LOG = LogManager.getLogger(CardClient.class.getName());

    public List<Card> receiveCards(String customer_no, String corrID)
    {

        LoggingMessage loggingMessage = new LoggingMessage(customer_no, corrID, "CardClient got a request, now sending request to db", "CardClient", "start");
        String logMessage = loggingMessage.toString();
        LOG.log(Level.INFO, logMessage);


        CardRequest request = CardRequest.newBuilder()
                .setCustomerNo(customer_no)
                .build();

        CardResponse response = cardServiceStub.getCards(request);

        LoggingMessage loggingMessage2 = new LoggingMessage(customer_no, corrID, "CardClient got a response from db and the returned card(s) are: " + response.getCardsList().toString(), "CardClient", "end");
        logMessage = loggingMessage2.toString();
        LOG.log(Level.INFO, logMessage);


        return response.getCardsList();
    }

}
