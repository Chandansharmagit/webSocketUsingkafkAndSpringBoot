package furniturewebsite_websocket.websocket.presentation.SecondTemplate;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class SecondsTemplateImpl {
    private int id;
    private String link;

    private MultipartFile returnImage;
    private byte[] image1;

}
