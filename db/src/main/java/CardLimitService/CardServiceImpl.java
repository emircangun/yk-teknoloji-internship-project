package CardLimitService;

import CardService.CardService;
import com.ykteknolojistaj.protointerface.Card;
import com.ykteknolojistaj.protointerface.CardRequest;
import com.ykteknolojistaj.protointerface.CardResponse;
import com.ykteknolojistaj.protointerface.CardServiceGrpc;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

import javax.persistence.Entity;
import java.math.BigDecimal;

@GrpcService
public class CardServiceImpl extends CardServiceGrpc.CardServiceImplBase {

    @Override
    public void getCards(CardRequest request, StreamObserver<CardResponse> responseObserver) {
        System.out.println("Request received from client:\n" + request);

        // Following code creates dummy cards for the sake of test
        // Instead of this, run sql queries to get actual data

        CardService cardService = new CardService();

        CardEntity.Card selectedCard = cardService.getCardLimitByCustomerNo(new BigDecimal((request.getCustomerNo())));

        CardResponse.Builder builder = CardResponse.newBuilder();

        builder.addCards(Card.newBuilder().setCardNo(selectedCard.getCardNo().toString()).setLimit(selectedCard.getLimit().doubleValue()).build());

        System.out.println(builder);

        CardResponse response = builder.build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
