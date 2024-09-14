package furniturewebsite_websocket.websocket.presentation.Client_Chat_WoodenNepal;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class User {
    @Id
    private String id;
    private String username;
    private String password;
    private boolean isAdmin;

    // Getters and setters
}
