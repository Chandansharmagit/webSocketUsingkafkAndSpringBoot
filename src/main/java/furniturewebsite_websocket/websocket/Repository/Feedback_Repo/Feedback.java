package furniturewebsite_websocket.websocket.Repository.Feedback_Repo;

import furniturewebsite_websocket.websocket.presentation.Product_feedback.Feedback_uploading;
import org.springframework.data.jpa.repository.JpaRepository;



public interface Feedback extends JpaRepository<Feedback_uploading,Integer> {

}
