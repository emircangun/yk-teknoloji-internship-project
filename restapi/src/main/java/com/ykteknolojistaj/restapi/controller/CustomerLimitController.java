package com.ykteknolojistaj.restapi.controller;

import com.ykteknolojistaj.restapi.CardGrpcClient.CardClient;
import com.ykteknolojistaj.protointerface.*;
import com.ykteknolojistaj.restapi.model.CardList;
import com.ykteknolojistaj.restapi.model.CardModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * RestController class handles all mapping from endpoints starts with '/api'.
 */
@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class CustomerLimitController {
    @Autowired
    private final CardClient cardService;

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

        try {
            // by using customer_no, getting all cards from the gRPC server (customer database microservice)
            // the return type is protointerface.card class
            List<Card> cardModels = cardService.receiveCards(customer_no);
            System.out.println("Taken cards from gRPC server with " + customer_no + ": " + cardModels);
            // copying all protointerface.card models to CardModel to use in response
            cardList.copyProtoCardArray(cardModels);
        } catch (io.grpc.StatusRuntimeException grpcRuntimeException) {
            // in case of an error while connecting to the gRPC service
            System.out.println("Error while fetching the data from the database microservice.");
            System.out.println(grpcRuntimeException);
        }

        return cardList.getCards();
    }


}