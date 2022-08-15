package CardLimitServiceImpl.CardLimitServer;

import CardLimitServiceImpl.CardServiceImpl;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Component
public class CardServer {
    public Server server;

    public void start(int port) throws IOException {

        server = ServerBuilder.forPort(port)
                .addService(new CardServiceImpl())
                .addService(ProtoReflectionService.newInstance())
                .build()
                .start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Shutting down gRPC server.");
            try {
                server.shutdown().awaitTermination(30, TimeUnit.SECONDS);
            } catch (InterruptedException e) {
                e.printStackTrace(System.err);
            }
        }));
    }
}
