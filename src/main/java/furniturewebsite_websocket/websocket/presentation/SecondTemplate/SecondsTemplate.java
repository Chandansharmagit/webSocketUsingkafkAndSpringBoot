package furniturewebsite_websocket.websocket.presentation.SecondTemplate;

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
@Table(name = "SecondTemplates")
public class SecondsTemplate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String link;
    @Lob
    @Column(columnDefinition = "LONGBLOB")
    private byte[] image;
}
