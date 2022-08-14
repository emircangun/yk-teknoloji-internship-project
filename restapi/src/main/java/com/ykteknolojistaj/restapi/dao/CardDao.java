package com.ykteknolojistaj.restapi.dao;

import builder.LogMessageBuilder;
import com.ykteknolojistaj.protointerface.Card;
import com.ykteknolojistaj.protointerface.CardRequest;
import com.ykteknolojistaj.protointerface.CardResponse;
import com.ykteknolojistaj.protointerface.CardServiceGrpc;
import net.devh.boot.grpc.client.inject.GrpcClient;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

@Component
public class CardDao {

    @GrpcClient("cardService")
    private CardServiceGrpc.CardServiceBlockingStub cardServiceStub;

    private static final Logger LOG = LogManager.getLogger(CardDao.class.getName());

    /**
     * Getting all cards of the given customer no by requesting it from the database microservice
     * @param customer_no given customer no to request from the database microservice
     * @param corrID unique id for the request
     * @return list of cards of the given customer no
     */
    public List<Card> receiveCards(String customer_no, String corrID)
    {
        // logging before the request
        LogMessageBuilder.Log(
                LOG, customer_no, corrID,
                this.getClass().getSimpleName(),
                this.getClass().getSimpleName() + " got a request, now sending request to db.",
                "start",
                Level.INFO
        );

        // building the request from the unique id and the customer no
        CardRequest request = CardRequest.newBuilder()
                .setCustomerNo(customer_no)
                .setCorrID(corrID)
                .build();

        // getting response from the service
        CardResponse response = cardServiceStub.getCards(request);

        // logging after the response
        LogMessageBuilder.Log(
                LOG, customer_no, corrID,
                this.getClass().getSimpleName(),
                this.getClass().getSimpleName() + " got a response from db and the returned card(s) are: " + response.getCardsList(),
                "end",
                Level.INFO
        );

        return response.getCardsList();
    }

}
