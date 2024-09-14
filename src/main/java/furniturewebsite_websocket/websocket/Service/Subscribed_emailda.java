package furniturewebsite_websocket.websocket.Service;

import furniturewebsite_websocket.websocket.presentation.EmailSaving.Email_Saving;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface Subscribed_emailda {
    public Email_Saving saving(Email_Saving email_saving);


    List<Email_Saving> get();
}
