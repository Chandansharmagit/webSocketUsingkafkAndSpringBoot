package furniturewebsite_websocket.websocket.presentation.Product_feedback;

import furniturewebsite_websocket.websocket.Repository.categoryDto;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "feedback_uploading")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Feedback_uploading {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String text;

    @Lob
    @Column(columnDefinition = "LONGBLOB") // Changed SQL type to LONGBLOB
    private byte[] image;


}
