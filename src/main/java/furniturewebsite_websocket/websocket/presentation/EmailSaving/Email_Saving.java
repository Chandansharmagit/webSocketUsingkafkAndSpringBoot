package furniturewebsite_websocket.websocket.presentation.EmailSaving;

import jakarta.persistence.*;

@Entity
@Table(name = "Email_details")
public class Email_Saving {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String email;

    // Default constructor
    public Email_Saving() {
    }

    // Parameterized constructor
    public Email_Saving(int id, String email) {
        this.id = id;
        this.email = email;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
