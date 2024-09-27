package furniturewebsite_websocket.websocket.Service.kafka_massaging_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


@Service
public class TrackingConsumer {
    @Autowired
    private WebSocketTrackingService webSocketTrackingService;

    @KafkaListener(topics = "live-tracking", groupId = "tracking-group")
    public void consume(String message) {
        System.out.println("Received tracking update: " + message);
        // Send the message to WebSocket clients
        webSocketTrackingService.sendUpdate(message);
    }

    @KafkaListener(topics = "admin-replies", groupId = "tracking-group")
    public void consumeAdminReply(String reply) {
        System.out.println("Received admin reply: " + reply);
        // Send the reply to WebSocket clients
        webSocketTrackingService.sendUpdateToClient(reply);
    }


}
