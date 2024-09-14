package furniturewebsite_websocket.websocket.Service;

import furniturewebsite_websocket.websocket.presentation.Message;
import org.springframework.stereotype.Service;

@Service
public interface massageData {
    public Message messagesaving(Message message);
}
