package furniturewebsite_websocket.websocket.presentation.Firsttemplate;

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
@Table(name = "firsttemplate") // Ensure this matches your database table name
public class Firsttemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(nullable = false) // Add constraints if needed
    private String recommendedText;

    @Column(nullable = false) // Add constraints if needed
    private String text;

    @Lob
    @Column(name = "image1", columnDefinition = "LONGBLOB") // Explicitly name the column if needed
    private byte[] image1;

    @Lob
    @Column(name = "image2", columnDefinition = "LONGBLOB") // Explicitly name the column if needed
    private byte[] image2;
}
