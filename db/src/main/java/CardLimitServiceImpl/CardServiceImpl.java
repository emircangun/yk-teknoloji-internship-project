package CardLimitServiceImpl;

import com.ykteknolojistaj.protointerface.Card;
import com.ykteknolojistaj.protointerface.CardRequest;
import com.ykteknolojistaj.protointerface.CardResponse;
import com.ykteknolojistaj.protointerface.CardServiceGrpc;
import dao.CardDao;
import io.grpc.stub.StreamObserver;

import net.devh.boot.grpc.server.service.GrpcService;

import java.util.NoSuchElementException;

@GrpcService
public class CardServiceImpl extends CardServiceGrpc.CardServiceImplBase {

    private CardDao cardDao = new CardDao(); // Database connection is done in cardDao

    /**
     * Overriding Protobuf getCards service. Getting customerNo from the request and
     * using it by calling cardDao.
     * @param request Request coming from REST API
     * @param responseObserver
     */
    /*
    @Override
    public void getCards(CardRequest request, StreamObserver<CardResponse> responseObserver) {
        System.out.println("Request received from client: " + request);

        CardResponse.Builder builder = CardResponse.newBuilder();

        try {
            //CardEntity.Card selectedCard = cardService.getCardLimitByCustomerNo(new BigDecimal((request.getCustomerNo())));
            CardEntity.Card selectedCard = cardDao.findByCustomerNo(request.getCustomerNo());

            // Building only one card from the coming card
            builder.addCards(
                    Card.newBuilder().
                            setCardNo(selectedCard.getCardNo().toString()).
                            setLimit(selectedCard.getLimit().doubleValue()).
                            build()
            );
        } catch (NoSuchElementException noElementException) {
            System.out.println(noElementException);
        }

        System.out.println(builder);

        // building the response to REST API
        CardResponse response = builder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
    */

    @Override
    public void getCards(CardRequest request, StreamObserver<CardResponse> responseObserver) {
        System.out.println("Request received from client:\n" + request);
        CardResponse.Builder builder = CardResponse.newBuilder();
        int size = Integer.parseInt(request.getCustomerNo());
        for (int i = 0; i < size; i++)
        {
            String card_no = "cardNoTest" + i;
            double limit = 100.0 * i;
            builder.addCards(Card.newBuilder().setCardNo(card_no).setLimit(limit).build());
        }

        System.out.println(builder);

        CardResponse response = builder.build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
