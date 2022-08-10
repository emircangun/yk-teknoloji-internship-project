package com.ykteknolojistaj.restapi.CardGrpcClient;

import com.ykteknolojistaj.protointerface.*;
import com.ykteknolojistaj.restapi.controller.CustomerLimitController;
import com.ykteknolojistaj.restapi.model.CardModel;
import com.ykteknolojistaj.restapi.model.LoggingMessage;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;
import net.devh.boot.grpc.client.inject.GrpcClient;
import java.util.List;

@Service
public class CardClient {

    private static final Logger LOG = LogManager.getLogger(CustomerLimitController.class.getName());
    @GrpcClient("cardService")
    private CardServiceGrpc.CardServiceBlockingStub cardServiceStub;

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
