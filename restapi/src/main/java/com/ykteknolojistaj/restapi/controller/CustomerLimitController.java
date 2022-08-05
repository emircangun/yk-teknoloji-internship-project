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

public class CustomerLimitController {
    @Autowired
    private final CardClient cardService;
    @GetMapping(path = "/get-cards", produces = "application/json")
    public List<CardModel> getCardsApi(@RequestParam(value = "customer_no") String customer_no) {

        List<Card> cardModels = cardService.receiveCards(customer_no);
        System.out.println(cardModels);
        CardList cardList = new CardList(cardModels);
        return cardList.getCards();
    }


}


/*
    @PostMapping("/getLimit")
    public ResponseEntity<CustomerLimitOutput> getCustomerLimitOutput(@RequestBody CustomerInfo customer_no)

    { if (customer_no.getCustomer_no()=="")
    {
        return new ResponseEntity("Costumer No alanları dolu olmalıdır", HttpStatus.BAD_REQUEST);
    }
        return new ResponseEntity("Costumer Kart ve Limiti:... ", HttpStatus.OK);
    }

  */