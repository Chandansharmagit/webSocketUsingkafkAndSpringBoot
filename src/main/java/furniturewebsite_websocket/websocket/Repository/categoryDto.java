package furniturewebsite_websocket.websocket.Repository;

import jakarta.persistence.Column;
import jakarta.persistence.Lob;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class categoryDto {
    private int id;

    private String name;

    private String text;

    private MultipartFile img;

    private byte[] returnedImg;
}
