package furniturewebsite_websocket.websocket.Service;

import furniturewebsite_websocket.websocket.Repository.massageSaving;
import furniturewebsite_websocket.websocket.presentation.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class massageService implements massageData{

    @Autowired
    private massageSaving massageSaving;
    @Override
    public Message messagesaving(Message message) {
        return massageSaving.save(message);
    }
}
