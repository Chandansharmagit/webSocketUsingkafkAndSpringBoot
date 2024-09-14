package furniturewebsite_websocket.websocket.Repository.Email_subscribed;

import furniturewebsite_websocket.websocket.presentation.EmailSaving.Email_Saving;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface Es extends JpaRepository<Email_Saving,Integer> {
    List<Email_Saving> getAllBy();
    public Email_Saving findByEmailContainingIgnoreCase(String query);

}
