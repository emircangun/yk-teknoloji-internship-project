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

import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.Pattern;

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
     * and by using it requesting corresponding cards from the gRPC server. Exceptions
     * are handled in exceptionHandler package.
     *
     * Example usage: /api/getCards?customer_no=1234567890
     *
     * @param customer_no Customer no parameter from the request
     * @return All cards which belong to the given customer
     */
    @GetMapping(path = "/getCards", produces = "application/json")
    public List<CardModel> getCardsApi(@RequestParam(value = "customer_no") @Pattern(regexp = "[1-9]{1}[0-9]{9}") String customer_no, HttpServletRequest request) {
        // initializing empty cardList
        CardList cardList = new CardList();
        request.setAttribute("corrID", "1asdfpıasjfawh9upfhuıfhausd");
        System.out.println(request);
        // creating unique id for the request and used it for logging
        String uniqueID = UUID.randomUUID().toString();

        LoggingMessage loggingMessage = new LoggingMessage(customer_no, uniqueID, "CustomerLimitController got a request, now calling CardClient", "CustomerLimitController", "start");
        String logMessage = loggingMessage.toString();
        LOG.log(Level.INFO, logMessage);

        //by using customer_no, getting all cards from the gRPC server (customer database microservice)
        // the return type is protointerface.card class
        List<Card> cardModels = cardService.receiveCards(customer_no, uniqueID);

        // copying all protointerface.card models to CardModel to use in response
        cardList.copyProtoCardArray(cardModels);

        // Loggging the response
        LoggingMessage loggingMessage2 = new LoggingMessage(customer_no, uniqueID, "Got a response from CardClient and the returned card(s) are: " + cardList.toString(), "CustomerLimitController", "end");
        logMessage = loggingMessage2.toString();
        LOG.log(Level.INFO, logMessage);

        return cardList.getCards();
    }

}