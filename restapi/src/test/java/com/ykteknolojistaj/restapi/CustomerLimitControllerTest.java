package com.ykteknolojistaj.restapi;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ykteknolojistaj.restapi.controller.CustomerLimitController;
import com.ykteknolojistaj.restapi.model.CardModel;
import com.ykteknolojistaj.restapi.service.CardService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerLimitController.class)
public class CustomerLimitControllerTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper mapper;
    @MockBean
    CardService cardService;


    @Test
    public void getCardsApi_success() throws Exception {
        List<CardModel> cardsOfCustomer = new ArrayList<>(Arrays.asList());

        Mockito.when(cardService.getCards(Mockito.anyString(), Mockito.anyString())).thenReturn(cardsOfCustomer);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/getCards")
                        .param("customer_no", "1234567891")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk())
                .andReturn();

    }

    @Test
    public void getCardsApi_CustomerNoLengthIsRight() throws Exception {
        List<CardModel> cardsOfCustomer = new ArrayList<>(Arrays.asList());

        Mockito.when(cardService.getCards(Mockito.anyString(), Mockito.anyString())).thenReturn(cardsOfCustomer);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/getCards")
                        .param("customer_no", "12345678")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isUnprocessableEntity())
                .andReturn();

    }

    @Test
    public void getCardsApi_CustomerNoIsNull() throws Exception {
        List<CardModel> cardsOfCustomer = new ArrayList<>(Arrays.asList());

        Mockito.when(cardService.getCards(Mockito.anyString(), Mockito.anyString())).thenReturn(cardsOfCustomer);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/getCards")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isBadRequest())
                .andReturn();


    }
}


