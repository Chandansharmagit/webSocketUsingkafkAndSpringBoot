package furniturewebsite_websocket.websocket.Service.TemlateUploadingServive_layer;

import furniturewebsite_websocket.websocket.Repository.FirstTemplateUploadingRepo.FirstTemplates_uploading;
import furniturewebsite_websocket.websocket.presentation.Firsttemplate.Firsttempl;
import furniturewebsite_websocket.websocket.presentation.Firsttemplate.Firsttemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemlateUploadingServive {

    @Autowired
    private FirstTemplates_uploading firstTemplatesUploading;


    public Firsttempl posting(Firsttempl firsttempl) throws IOException {
        Firsttemplate firsttemplate = new Firsttemplate();
        firsttemplate.setRecommendedText(firsttempl.getRecommended());
        firsttemplate.setText(firsttempl.getText1());
        firsttemplate.setImage1(firsttempl.getImage1().getBytes());
        firsttemplate.setImage2(firsttempl.getImage2().getBytes());

        Firsttemplate newtemplate = firstTemplatesUploading.save(firsttemplate);
        Firsttempl newfirsttempl = new Firsttempl();
        newfirsttempl.setId(newtemplate.getId());
        return newfirsttempl;
    }


    public List<Firsttempl> getalltemplates() {
        List<Firsttemplate> templatesgetting = firstTemplatesUploading.findAll();
        return templatesgetting.stream()
                .map(this::convertingToFirsttempl)
                .collect(Collectors.toList());
    }

    private Firsttempl convertingToFirsttempl(Firsttemplate firsttemplate) {
        Firsttempl newfirsttempl = new Firsttempl();
        newfirsttempl.setId(firsttemplate.getId());
        newfirsttempl.setRecommended(firsttemplate.getRecommendedText());
        newfirsttempl.setText1(firsttemplate.getText());
        newfirsttempl.setReturnimage1(firsttemplate.getImage1());
        newfirsttempl.setReturnimage2(firsttemplate.getImage2());
        return newfirsttempl;
    }

   
    public void deleteTemplate(int id) {
        firstTemplatesUploading.deleteById(id);
    }
}
