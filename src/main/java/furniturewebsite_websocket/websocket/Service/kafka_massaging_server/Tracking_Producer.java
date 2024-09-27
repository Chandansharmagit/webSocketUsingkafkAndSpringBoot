package furniturewebsite_websocket.websocket.Service.kafka_massaging_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class Tracking_Producer {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(String message) {
        kafkaTemplate.send("live-tracking", message);
    }

    public void sendAdminReply(String reply) {
        kafkaTemplate.send("admin-replies", reply);
    }
}
