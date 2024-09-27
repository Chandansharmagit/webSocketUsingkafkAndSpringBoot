package furniturewebsite_websocket.websocket.Controller;


import furniturewebsite_websocket.websocket.Repository.Email_subscribed.Es;


import furniturewebsite_websocket.websocket.Repository.categoryDto;
import furniturewebsite_websocket.websocket.Repository.massageSaving;

import furniturewebsite_websocket.websocket.Service.Feedbacks.Feedback_products_serviceImpl;
import furniturewebsite_websocket.websocket.Service.SecondsTemplateServicelayer.SecondaTemplateService;
import furniturewebsite_websocket.websocket.Service.SubscribedemailService;
import furniturewebsite_websocket.websocket.Service.TemlateUploadingServive_layer.TemlateUploadingServive;
import furniturewebsite_websocket.websocket.Service.TemplateUploding_four_Service_layer.TemplateUploding_fourService_layer;
import furniturewebsite_websocket.websocket.Service.kafka_massaging_server.Tracking_Producer;
import furniturewebsite_websocket.websocket.Service.massageData;
import furniturewebsite_websocket.websocket.presentation.Client_Chat_WoodenNepal.WoodenNepal;
import furniturewebsite_websocket.websocket.presentation.EmailSaving.Email_Saving;
import furniturewebsite_websocket.websocket.presentation.Firsttemplate.Firsttempl;
import furniturewebsite_websocket.websocket.presentation.Message;


import furniturewebsite_websocket.websocket.presentation.SecondTemplate.SecondsTemplateImpl;
import furniturewebsite_websocket.websocket.presentation.TemplateFour.ConvertTemplateFour;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000") // Adjust the origin to match your frontend URL

public class Controller {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @Autowired
    private massageData massageData;

    @Autowired
    private massageSaving massageSaving;

    @Autowired
    private SubscribedemailService es;

    @Autowired
    private Es se;






    // Email Subscriptions

    @PostMapping("/subscribed_email_saving")
    public Email_Saving email_saving(@RequestBody Email_Saving email_saving) {
        return es.saving(email_saving);
    }

    @DeleteMapping("/api/subscribed-emails/{id}")
    public ResponseEntity<Void> deleteSubscribedEmail(@PathVariable int id) {
        se.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/Subscribed_emailGett")
    public ResponseEntity<List<Email_Saving>> getSubscribed_email() {
        try {
            List<Email_Saving> emails = se.getAllBy();
            return new ResponseEntity<>(emails, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Image and Feedback Handling

    @Autowired
    private Feedback_products_serviceImpl feedbackService;


    @PostMapping("/postCategory")
    public ResponseEntity<categoryDto> postCategory(@ModelAttribute categoryDto categoryDto) throws IOException {
        categoryDto categoryDto1 = feedbackService.postCategory(categoryDto);
        if (categoryDto1 == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(categoryDto1);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<categoryDto>> getAll() {
        List<categoryDto> categoryDtos = feedbackService.getAll();
        if (categoryDtos.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(categoryDtos);
        }

    }

    @DeleteMapping("/deleting_Feedback/{id}")
    public ResponseEntity<Void> deleteFeedback(@PathVariable int id) {
        try {
            feedbackService.deletingevent(id);
            return ResponseEntity.noContent().build(); // Return 204 No Content if deletion is successful
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build(); // Return 404 Not Found if feedback is not found
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // Handle any other exceptions
        }
    }


    //making the save of Firsttemplate
    @Autowired
    private TemlateUploadingServive temlateUploadingServive;


    @PostMapping("/first-template-uploading")
    public ResponseEntity<?> uploadTemplate(
            @RequestParam("recommendedText") String recommendedText,
            @RequestParam("text") String text,
            @RequestParam("image1") MultipartFile image1,
            @RequestParam("image2") MultipartFile image2) throws IOException {

        Firsttempl firsttempl = new Firsttempl();
        firsttempl.setRecommended(recommendedText);
        firsttempl.setText1(text);
        firsttempl.setImage1(image1);
        firsttempl.setImage2(image2);

        Firsttempl result = temlateUploadingServive.posting(firsttempl);

        return ResponseEntity.ok(result);
    }


    @GetMapping("/getting_templates_uploading")
    public ResponseEntity<List<Firsttempl>> getall() {
        List<Firsttempl> getting = temlateUploadingServive.getalltemplates();
        if (getting.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(getting);
        }
    }

    //deleting method should be creat here


    //making the implementation of Secondstemplate uploading and getting and delting

    @Autowired
    private SecondaTemplateService secondsTemplateService;

    @PostMapping("/posting_SecondsTemplate")
    public ResponseEntity<?> postingg(@RequestParam("link") String link, @RequestParam("image") MultipartFile image) throws IOException {
        SecondsTemplateImpl secondsTemplate = new SecondsTemplateImpl();
        secondsTemplate.setLink(link);
        secondsTemplate.setImage1(image.getBytes());
        SecondsTemplateImpl saving = secondsTemplateService.postingdata(secondsTemplate);
        return ResponseEntity.ok().body(saving);
    }

    //getting the method


    @GetMapping("/gettingSeconds_templates_uploading")
    public ResponseEntity<List<SecondsTemplateImpl>> getalldata() throws Exception {
        List<SecondsTemplateImpl> getting = secondsTemplateService.gettingdata();
        if (getting.isEmpty()) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.ok(getting);
        }
    }


    @DeleteMapping("/deleting_SecondsTemplate/{id}")
    public ResponseEntity<?> deletingevent(@PathVariable int id) {
        secondsTemplateService.deletingwithId(id);
        return ResponseEntity.noContent().build();
    }


    @PutMapping("/updating_SecondsTemplate/{id}")
    public ResponseEntity<SecondsTemplateImpl> updateTemplate(@PathVariable int id, @RequestParam("link") String link, @RequestParam("image") MultipartFile image) {
        try {
            SecondsTemplateImpl updatedTemplate = new SecondsTemplateImpl();
            updatedTemplate.setLink(link);
            updatedTemplate.setImage1(image.getBytes());

            SecondsTemplateImpl savedTemplate = secondsTemplateService.updatedata(id, updatedTemplate);
            return ResponseEntity.ok(savedTemplate);
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (IllegalArgumentException e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    //making the methods for last two templates

    @Autowired
    private TemplateUploding_fourService_layer TemplateUploadingServiceLayer;


    @PostMapping("/uploading_two")
    public ResponseEntity<ConvertTemplateFour> postindata(@RequestParam("image") MultipartFile image, @RequestParam("link") String link) throws IOException {
        ConvertTemplateFour convertTemplateFour = new ConvertTemplateFour();
        convertTemplateFour.setImage(image);
        convertTemplateFour.setLink(link);
        ConvertTemplateFour saving = TemplateUploadingServiceLayer.savingdata(convertTemplateFour);
        return ResponseEntity.ok(saving);
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<ConvertTemplateFour> updateTemplate(@PathVariable int id, @ModelAttribute ConvertTemplateFour updatedTemplate) throws IOException {
        ConvertTemplateFour updatedData = TemplateUploadingServiceLayer.updatedata(id, updatedTemplate);
        return ResponseEntity.ok(updatedData);
    }

    @GetMapping("/getting_updating_data")

    public ResponseEntity<List<ConvertTemplateFour>> getAllTemplates() throws Exception {
        List<ConvertTemplateFour> templates = TemplateUploadingServiceLayer.gettingdata();
        return ResponseEntity.ok(templates);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ConvertTemplateFour> deleteTemplate(@PathVariable int id) {
        ConvertTemplateFour deletedTemplate = TemplateUploadingServiceLayer.deletingwithId(id);
        return ResponseEntity.ok(deletedTemplate);
    }


    //making the search opeations

    @GetMapping("/searching/{query}")
    public ResponseEntity<List<Email_Saving>> searching(@PathVariable String query){
        Email_Saving saving = es.finding(query).getBody();
        if(saving == null){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(Collections.singletonList(saving));
        }

    }












    @Autowired
    private Tracking_Producer trackingProducer;



    // Endpoint for sending messages
    @MessageMapping("/sendMessage")
    @SendTo("/topic/chat")
    public Message sendMessage(String message) {
        // Optionally, send the message to Kafka or another service
        trackingProducer.sendMessage(message);

        // Create a response JSON object
        return new Message("Server received: " + message);
    }

    // Handle admin replies (can be used to send replies back to clients)
    @MessageMapping("/sendAdminReply")
    @SendTo("/topic/admin-replies")
    public Message handleAdminReply(Message message) {
        // Return the message to be sent to WebSocket clients
        return new Message("Admin replied: " + message.getContent());
    }

    public static class Message {
        private String content;

        public Message() {}

        public Message(String content) {
            this.content = content;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }

}
