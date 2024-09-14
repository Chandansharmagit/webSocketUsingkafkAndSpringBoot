package furniturewebsite_websocket.websocket.Service.TemplateUploding_four_Service_layer;

import furniturewebsite_websocket.websocket.Repository.TemlateUploadingFourRepository.TemplateUploadingFourRepository;

import furniturewebsite_websocket.websocket.presentation.TemplateFour.ConvertTemplateFour;
import furniturewebsite_websocket.websocket.presentation.TemplateFour.TemplateFour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TemplateUploding_fourService_layer {
    @Autowired
    private  TemplateUploadingFourRepository TemplateUploadingFourRepo;

    public ConvertTemplateFour savingdata(@ModelAttribute ConvertTemplateFour convertTemplateFour) throws IOException {
        TemplateFour secondsTemplate1 = new TemplateFour();

        secondsTemplate1.setLink(convertTemplateFour.getLink());
        secondsTemplate1.setImage(convertTemplateFour.getImage().getBytes());
        TemplateFour secondsTemplate2 = TemplateUploadingFourRepo.save(secondsTemplate1);
        ConvertTemplateFour secondsTemplate3 = new ConvertTemplateFour();
        secondsTemplate3.setId(secondsTemplate2.getId());
        return secondsTemplate3;
    }

    // This method for updating data in the database
    public ConvertTemplateFour updatedata(int id, @ModelAttribute ConvertTemplateFour updatedTemplate) throws IOException {
        TemplateFour existingTemplate = TemplateUploadingFourRepo.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Seconds template not found"));

        // Update the existing template with new data
        existingTemplate.setLink(updatedTemplate.getLink());
        existingTemplate.setImage(updatedTemplate.getImageByte());

        // Save the updated template back to the database
        TemplateFour savedTemplate = TemplateUploadingFourRepo.save(existingTemplate);
        return convertsecondsTemplate(savedTemplate);
    }

    private ConvertTemplateFour convertsecondsTemplate(TemplateFour secondsTemplate){
        ConvertTemplateFour secondsTemplate1 = new ConvertTemplateFour();
        secondsTemplate1.setLink(secondsTemplate.getLink());
        secondsTemplate1.setImageByte(secondsTemplate.getImage());
        secondsTemplate1.setId(secondsTemplate.getId());
        return secondsTemplate1;

    }

    public List<ConvertTemplateFour> gettingdata()throws Exception{
        List<TemplateFour> secondsTemplates = TemplateUploadingFourRepo.findAll();
        return secondsTemplates.stream().map(this::convertsecondsTemplate).collect(Collectors.toList());


    }


    //deleting that events with the id

    public ConvertTemplateFour deletingwithId(int id){
        TemplateFour secondsTemplate = TemplateUploadingFourRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("seconds template not found"));
        TemplateUploadingFourRepo.delete(secondsTemplate);
        return convertsecondsTemplate(secondsTemplate);
    }

}
