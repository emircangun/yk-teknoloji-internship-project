package com.ykteknolojistaj.restapi.controller;

import com.ykteknolojistaj.restapi.grpcClient.CardLimitClient;
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
import com.ykteknolojistaj.restapi.service.GetCardsService;

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
    private final CardLimitClient grpcLimitClient;

    private final GetCardsService getCardsService;

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
        // creating unique id for the request and used it for logging
        String uniqueID = UUID.randomUUID().toString();

        LoggingMessage loggingMessage = new LoggingMessage(customer_no, uniqueID, "CustomerLimitController got a request, now calling GetCardsService", "CustomerLimitController", "start");
        String logMessage = loggingMessage.toString();
        LOG.log(Level.INFO, logMessage);

        return getCardsService.getCards(grpcLimitClient, customer_no, uniqueID);
    }

}