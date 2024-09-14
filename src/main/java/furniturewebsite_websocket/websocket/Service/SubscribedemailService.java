package furniturewebsite_websocket.websocket.Service;

import furniturewebsite_websocket.websocket.Repository.Email_subscribed.Es;
import furniturewebsite_websocket.websocket.presentation.EmailSaving.Email_Saving;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscribedemailService implements Subscribed_emailda{
    @Autowired
    private Es es;
    @Override
    public Email_Saving saving(Email_Saving email_saving) {
        return es.save(email_saving);
    }

    @Override
    @Cacheable("messages")
    public List<Email_Saving> get() {
        return es.findAll();
    }

    //deleting the email by id

    public void delete(int id){
        es.deleteById(id);
    }

    //making the searching for emails

    public ResponseEntity<Email_Saving> finding(String query){
        Email_Saving email_saving = es.findByEmailContainingIgnoreCase(query);
        if(email_saving == null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(email_saving);
        }
    }


}
