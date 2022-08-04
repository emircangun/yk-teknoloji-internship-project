package CardLimitService;

import com.ykteknolojistaj.protointerface.*;
import io.grpc.stub.StreamObserver;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
public class CardServiceImpl extends CardServiceGrpc.CardServiceImplBase {

    @Override
    public void getCards(CardRequest request, StreamObserver<CardResponse> responseObserver) {
        System.out.println("Request received from client:\n" + request);

        // Following code creates dummy cards for the sake of test
        // Instead of this, run sql queries to get actual data
        CardResponse.Builder builder = CardResponse.newBuilder();
        int size = Integer.parseInt(request.getCustomerNo());
        for (int i = 0; i < size; i++)
        {
            String card_no = "cardNoTest" + i;
            double limit = 100.0 * i;
            builder.addCards(Card.newBuilder().setCardNo(card_no).setLimit(limit).build());
        }
        //


        System.out.println(builder);

        CardResponse response = builder.build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

}
