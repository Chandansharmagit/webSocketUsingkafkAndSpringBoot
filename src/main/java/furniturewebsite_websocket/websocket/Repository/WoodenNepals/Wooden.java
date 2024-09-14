package furniturewebsite_websocket.websocket.Repository.WoodenNepals;

import furniturewebsite_websocket.websocket.presentation.Client_Chat_WoodenNepal.WoodenNepal;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Wooden extends JpaRepository<WoodenNepal,Integer> {
}
