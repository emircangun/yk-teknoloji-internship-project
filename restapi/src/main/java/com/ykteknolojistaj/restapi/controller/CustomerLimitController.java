package com.ykteknolojistaj.restapi.controller;

import com.ykteknolojistaj.restapi.CardGrpcClient.CardClient;
import com.ykteknolojistaj.protointerface.*;
import com.ykteknolojistaj.restapi.model.CardList;
import com.ykteknolojistaj.restapi.model.CardModel;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@Slf4j
@Validated
@RequiredArgsConstructor
@RequestMapping(path = "/api")
public class CustomerLimitController {
    @Autowired
    private final CardClient cardService;

    @GetMapping(path = "/getCards", produces = "application/json")
    public List<CardModel> getCardsApi(@RequestParam(value = "customer_no") String customer_no) {
        CardList cardList = new CardList();

        try {
            List<Card> cardModels = cardService.receiveCards(customer_no);
            System.out.println(cardModels);
            cardList.copyProtoCardArray(cardModels);
        } catch (io.grpc.StatusRuntimeException grpcRuntimeException) {
            System.out.println("Error while fetching the data from the database microservice.");
            System.out.println(grpcRuntimeException);
        }

        return cardList.getCards();
    }


}