package com.ykteknolojistaj.restapi.controller;

import builder.LogMessageBuilder;
import com.ykteknolojistaj.restapi.exceptionhandler.ExceptionTemplate;
import com.ykteknolojistaj.restapi.model.CardModel;
import com.ykteknolojistaj.restapi.service.CardService;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    private final CardService cardService;

    private static final Logger LOG = LogManager.getLogger(CustomerLimitController.class.getName());


    @ApiResponses(value = {
            @ApiResponse( responseCode = "200", description = "Cards are found.",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = CardModel.class))} ),
            @ApiResponse( responseCode = "400", description = "Request parameter is missing.",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionTemplate.class))} ),
            @ApiResponse( responseCode = "404", description = "Database connection error.",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionTemplate.class))} ),
            @ApiResponse( responseCode = "422", description = "Parameters must provide conditions.",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ExceptionTemplate.class))})
            }
    )
    @GetMapping(path = "/getCards", produces = "application/json")
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
    public List<CardModel> getCardsApi(@RequestParam(value = "customer_no") @Pattern(regexp = "[1-9][0-9]{9}") String customer_no) {
        // creating unique id for the request and used it for logging
        String uniqueID = UUID.randomUUID().toString();

        // logging before getting cards
        LogMessageBuilder.Log(
                LOG, customer_no, uniqueID,
                this.getClass().getSimpleName(),
                this.getClass().getSimpleName() + " got a request, now calling CardService",
                "start",
                Level.INFO
        );

        // getting cards from database microservice
        List<CardModel> cardsOfCustomer = cardService.getCards(customer_no, uniqueID);

        // logging after handling the request
        LogMessageBuilder.Log(
                LOG, customer_no, uniqueID,
                this.getClass().getSimpleName(),
                this.getClass().getSimpleName() + " handled the request, returns: " + cardsOfCustomer,
                "end",
                Level.INFO
        );

        return cardsOfCustomer;
    }

}