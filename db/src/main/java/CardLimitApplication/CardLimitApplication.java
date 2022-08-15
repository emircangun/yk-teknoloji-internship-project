package CardLimitApplication;

import CardLimitServer.CardServer;
import java.io.IOException;

public class CardLimitApplication {

    public static void main(String[] args) throws IOException, InterruptedException {
        final CardServer cardServer = new CardServer();
        cardServer.start(9091);
        cardServer.server.awaitTermination();
    }

}
