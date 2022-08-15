package CardLimitServiceImpl;

import builder.LogMessageBuilder;
import com.ykteknolojistaj.protointerface.Card;
import com.ykteknolojistaj.protointerface.CardRequest;
import com.ykteknolojistaj.protointerface.CardResponse;
import com.ykteknolojistaj.protointerface.CardServiceGrpc;
import dao.CardDao;
import io.grpc.stub.StreamObserver;


import net.devh.boot.grpc.server.service.GrpcService;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;
import java.util.NoSuchElementException;


@GrpcService
public class CardServiceImpl extends CardServiceGrpc.CardServiceImplBase {

    private final CardDao cardDao = new CardDao(); // Database connection is done in cardDao

    private static final Logger LOG = LogManager.getLogger(CardServiceImpl.class.getName());

    /**
     * Overriding Protobuf getCards service. Getting customerNo from the request and
     * using it by calling cardDao.
     * @param request Request coming from REST API
     * @param responseObserver
     */

    @Override
    public void getCards(CardRequest request, StreamObserver<CardResponse> responseObserver) {

        String uniqueID = request.getCorrID();

        LogMessageBuilder.Log(
                LOG, request.getCustomerNo(), uniqueID,
                this.getClass().getSimpleName(),
                "DB recieved request from client: " + request,
                "start",
                Level.INFO
        );

        CardResponse.Builder builder = CardResponse.newBuilder();

        try {
            List<CardEntity.Card> cardList = cardDao.findByCustomerNo(request.getCustomerNo(), uniqueID);
            for(CardEntity.Card selectedCard :cardList)
            {
                //Building only one card from the coming cards
                builder.addCards(
                        Card.newBuilder().
                                setCardNo(selectedCard.getCardNo()).
                                setLimit(selectedCard.getLimit().doubleValue()).
                                build()
                );
            }

            LogMessageBuilder.Log(
                    LOG, request.getCustomerNo(), uniqueID,
                    this.getClass().getSimpleName(),
                    "DB found card(s)" + builder,
                    "end",
                    Level.INFO
            );

        } catch (NoSuchElementException noElementException) {

            LogMessageBuilder.Log(
                    LOG, request.getCustomerNo(), uniqueID,
                    this.getClass().getSimpleName(),
                    "DB did not found any card",
                    "end",
                    Level.ERROR
            );
        }

        //System.out.println(builder);

        // building the response to REST API
        CardResponse response = builder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }



}
