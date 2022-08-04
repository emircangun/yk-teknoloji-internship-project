package com.ykteknolojistaj.restapi.CardGrpcClient;

import com.ykteknolojistaj.protointerface.*;

import org.springframework.stereotype.Service;
import net.devh.boot.grpc.client.inject.GrpcClient;
import java.util.List;

@Service
public class CardClient {

    @GrpcClient("cardService")
    private CardServiceGrpc.CardServiceBlockingStub cardServiceStub;

    public List<Card> receiveCards(String customer_no)
    {
        CardRequest request = CardRequest.newBuilder()
                .setCustomerNo(customer_no)
                .build();

        CardResponse response = cardServiceStub.getCards(request);
        return response.getCardsList();
    }

}
