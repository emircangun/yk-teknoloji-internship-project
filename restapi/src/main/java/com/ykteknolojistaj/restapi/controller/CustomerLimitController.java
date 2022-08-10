package com.ykteknolojistaj.restapi.controller;

import com.ykteknolojistaj.restapi.CardGrpcClient.CardClient;
import com.ykteknolojistaj.protointerface.*;
import com.ykteknolojistaj.restapi.model.CardList;
import com.ykteknolojistaj.restapi.model.CardModel;
import com.ykteknolojistaj.restapi.model.LoggingMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * RestController class handles all mapping from endpoints starts with '/api'.
 */
@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@CrossOrigin(origins="http://localhost:3000", methods={RequestMethod.GET, RequestMethod.PUT,RequestMethod.DELETE,RequestMethod.POST})
@RequestMapping(path = "/api")
public class CustomerLimitController {
    @Autowired
    private final CardClient cardService;
    private static final Logger LOG = LogManager.getLogger(CustomerLimitController.class.getName());

    /**
     * Get mapping on '/api/getCards' endpoint. Uses one parameter as 'customer_no'
     * and by using it requesting corresponding cards from the gRPC server.
     *
     * @param customer_no Customer no parameter from the request
     * @return All cards which belong to the given customer
     */
    @GetMapping(path = "/getCards", produces = "application/json")
    public List<CardModel> getCardsApi(@RequestParam(value = "customer_no") String customer_no) {
        // initializing empty cardList
        CardList cardList = new CardList();
        String uniqueID = UUID.randomUUID().toString();

        try {
            // by using customer_no, getting all cards from the gRPC server (customer database microservice)
            // the return type is protointerface.card class

            LoggingMessage loggingMessage = new LoggingMessage(customer_no, uniqueID, "CustomerLimitController got a request, now calling CardClient", "CustomerLimitController", "start");
            String logMessage = loggingMessage.toString();
            LOG.log(Level.INFO, logMessage);


            List<Card> cardModels = cardService.receiveCards(customer_no, uniqueID);
            //System.out.println("Taken cards from gRPC server with " + customer_no + ": " + cardModels);
            // copying all protointerface.card models to CardModel to use in response
            cardList.copyProtoCardArray(cardModels);

            LoggingMessage loggingMessage2 = new LoggingMessage(customer_no, uniqueID, "Got a response from CardClient and the returned card(s) are: " + cardList.toString(), "CustomerLimitController", "end");
            logMessage = loggingMessage2.toString();
            LOG.log(Level.INFO, logMessage);

        } catch (io.grpc.StatusRuntimeException grpcRuntimeException) {
            // in case of an error while connecting to the gRPC service
            //System.out.println("Error while fetching the data from the database microservice.");
            //System.out.println(grpcRuntimeException);

            LoggingMessage loggingMessage3 = new LoggingMessage(customer_no, uniqueID, "Error while fetching the data from the database microservice.", "CustomerLimitController", "end");
            String logMessage = loggingMessage3.toString();
            LOG.log(Level.ERROR, logMessage);

        }

        return cardList.getCards();
    }


}