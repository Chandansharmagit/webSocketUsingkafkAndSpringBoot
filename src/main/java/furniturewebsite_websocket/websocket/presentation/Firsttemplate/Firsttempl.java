package furniturewebsite_websocket.websocket.presentation.Firsttemplate;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class Firsttempl {
    private int id;
    private String recommended; // Corrected spelling
    private String text1;
    private MultipartFile image1;
    private MultipartFile image2;
    private byte[] returnimage1;
    private byte[] returnimage2;
}

