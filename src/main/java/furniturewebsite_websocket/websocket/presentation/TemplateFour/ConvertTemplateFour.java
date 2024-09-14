package furniturewebsite_websocket.websocket.presentation.TemplateFour;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ConvertTemplateFour {
    private int id;
    private String Link;
    private MultipartFile image;
    private byte[] imageByte;
}
