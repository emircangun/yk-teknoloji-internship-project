package CardLimitServiceImpl;

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
import java.util.UUID;

@GrpcService
public class CardServiceImpl extends CardServiceGrpc.CardServiceImplBase {

    private CardDao cardDao = new CardDao(); // Database connection is done in cardDao

    private static final Logger LOG = LogManager.getLogger(CardServiceImpl.class.getName());

    /**
     * Overriding Protobuf getCards service. Getting customerNo from the request and
     * using it by calling cardDao.
     * @param request Request coming from REST API
     * @param responseObserver
     */

    @Override
    public void getCards(CardRequest request, StreamObserver<CardResponse> responseObserver) {
        //System.out.println("Request received from client: " + request);

        String uniqueID = UUID.randomUUID().toString();
        //Response icerigi ogrenilip customer no log constructer icine yazilacak
        LoggingMessage loggingMessage = new LoggingMessage(request.getCustomerNo(), uniqueID, "DB recieved request from client: " + request, "getCards", "start");
        String logMessage = loggingMessage.toString();
        LOG.log(Level.INFO, logMessage);

        CardResponse.Builder builder = CardResponse.newBuilder();

        try {
            List<CardEntity.Card> cardList = cardDao.findByCustomerNo(request.getCustomerNo());
            for(CardEntity.Card selectedCard :cardList)
            {
                //Building only one card from the coming card
                builder.addCards(
                        Card.newBuilder().
                                setCardNo(selectedCard.getCardNo().toString()).
                                setLimit(selectedCard.getLimit().doubleValue()).
                                build()
                );
            }

            LoggingMessage loggingMessage2 = new LoggingMessage(request.getCustomerNo(), uniqueID, "DB found card(s)" + builder, "getCards", "end");
            logMessage = loggingMessage2.toString();
            LOG.log(Level.INFO, logMessage);

        } catch (NoSuchElementException noElementException) {
            //System.out.println(noElementException);
            LoggingMessage loggingMessage3 = new LoggingMessage(request.getCustomerNo(), uniqueID, "DB did not found any card", "getCards", "end");
            logMessage = loggingMessage3.toString();
            LOG.log(Level.ERROR, logMessage);
        }

        //System.out.println(builder);

        // building the response to REST API
        CardResponse response = builder.build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }



}
