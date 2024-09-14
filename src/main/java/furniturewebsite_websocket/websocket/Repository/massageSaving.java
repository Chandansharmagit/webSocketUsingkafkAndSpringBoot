package furniturewebsite_websocket.websocket.Repository;

import furniturewebsite_websocket.websocket.presentation.Message;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface massageSaving extends JpaRepository<Message,Integer> {
    List<Message> findByReceiverName(String receiverName);
}
