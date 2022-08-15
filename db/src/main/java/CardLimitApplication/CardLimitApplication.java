package CardLimitApplication;

import CardLimitServiceImpl.CardLimitServer.CardServer;
import java.io.IOException;

public class CardLimitApplication {

    // exceptions do not be handled
    public static void main(String[] args) throws IOException, InterruptedException {
        final CardServer cardServer = new CardServer();
        cardServer.start();
        cardServer.server.awaitTermination();
    }


}
