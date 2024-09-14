package furniturewebsite_websocket.websocket.Service.SecondsTemplateServicelayer;

import furniturewebsite_websocket.websocket.Repository.SecondTemplateRepo.SeondTempltesRepository;
import furniturewebsite_websocket.websocket.presentation.SecondTemplate.SecondsTemplate;
import furniturewebsite_websocket.websocket.presentation.SecondTemplate.SecondsTemplateImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.util.List;

import java.util.stream.Collectors;

@Service
public class SecondaTemplateService {
    @Autowired
    private SeondTempltesRepository seondTempltesRepository;


    //this method for posting data into the database
    public SecondsTemplateImpl postingdata(@ModelAttribute SecondsTemplateImpl secondsTemplate) throws IOException {
        SecondsTemplate secondsTemplate1 = new SecondsTemplate();

        secondsTemplate1.setLink(secondsTemplate.getLink());
        secondsTemplate1.setImage(secondsTemplate.getImage1());
        SecondsTemplate secondsTemplate2 = seondTempltesRepository.save(secondsTemplate1);
        SecondsTemplateImpl secondsTemplate3 = new SecondsTemplateImpl();
        secondsTemplate3.setId(secondsTemplate2.getId());
        return secondsTemplate3;
    }



    // This method for updating data in the database
    public SecondsTemplateImpl updatedata(int id, @ModelAttribute SecondsTemplateImpl updatedTemplate) throws IOException {
        SecondsTemplate existingTemplate = seondTempltesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Seconds template not found"));

        // Update the existing template with new data
        existingTemplate.setLink(updatedTemplate.getLink());
        existingTemplate.setImage(updatedTemplate.getImage1());

        // Save the updated template back to the database
        SecondsTemplate savedTemplate = seondTempltesRepository.save(existingTemplate);
        return convertsecondsTemplate(savedTemplate);
    }


    private SecondsTemplateImpl convertsecondsTemplate(SecondsTemplate secondsTemplate){
        SecondsTemplateImpl secondsTemplate1 = new SecondsTemplateImpl();
        secondsTemplate1.setLink(secondsTemplate.getLink());
        secondsTemplate1.setImage1(secondsTemplate.getImage());
        secondsTemplate1.setId(secondsTemplate.getId());
        return secondsTemplate1;

    }

    //now getting the data from database

    public List<SecondsTemplateImpl> gettingdata()throws Exception{
        List<SecondsTemplate> secondsTemplates = seondTempltesRepository.findAll();
        return secondsTemplates.stream().map(this::convertsecondsTemplate).collect(Collectors.toList());


    }


    //deleting that events with the id

    public SecondsTemplateImpl deletingwithId(int id){
        SecondsTemplate secondsTemplate = seondTempltesRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("seconds template not found"));
        seondTempltesRepository.delete(secondsTemplate);
        return convertsecondsTemplate(secondsTemplate);
    }
}
