package furniturewebsite_websocket.websocket.Service.kafka_massaging_server;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
public class WebSocketTrackingService {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    // Send updates to WebSocket clients
    public void sendUpdate(String message) {
        messagingTemplate.convertAndSend("/topic/tracking", message);
    }

    public void sendUpdateToClient(String reply) {
        messagingTemplate.convertAndSend("/topic/admin-replies", new Message(reply));
    }

    public static class Message {
        private String content;

        public Message(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
