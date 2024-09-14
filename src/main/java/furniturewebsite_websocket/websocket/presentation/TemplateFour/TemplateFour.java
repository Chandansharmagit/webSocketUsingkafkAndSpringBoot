package furniturewebsite_websocket.websocket.presentation.TemplateFour;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "TemplateFoutNepal_finest_online_furniture_Brand")
public class TemplateFour {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String Link;
    @Lob
    @Column(name = "image", columnDefinition = "LONGBLOB")
    private byte[] image;
}
