package furniturewebsite_websocket.websocket.Service.Feedbacks;

import furniturewebsite_websocket.websocket.Repository.Feedback_Repo.Feedback;
import furniturewebsite_websocket.websocket.Repository.categoryDto;
import furniturewebsite_websocket.websocket.presentation.Product_feedback.Feedback_uploading;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ModelAttribute;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class Feedback_products_serviceImpl implements Feedback_products_service {

    @Autowired
    private Feedback feedback;



    public categoryDto postCategory(@ModelAttribute categoryDto categoryDto) throws IOException {
        Feedback_uploading feedbackUploading = new Feedback_uploading();
        feedbackUploading.setName(categoryDto.getName());
        feedbackUploading.setText(categoryDto.getText());
        feedbackUploading.setImage(categoryDto.getImg().getBytes());

        Feedback_uploading createdFeedback = feedback.save(feedbackUploading);
        categoryDto createdCategoryDto = new categoryDto();
        createdCategoryDto.setId(createdFeedback.getId());
        return createdCategoryDto;
    }


    @Cacheable("feedback_data")
    public List<categoryDto> getAll() {
        List<Feedback_uploading> feedbackList = feedback.findAll();
        return feedbackList.stream()
                .map(this::convertToCategoryDto) // Convert each Feedback_uploading to categoryDto
                .collect(Collectors.toList());
    }

    private categoryDto convertToCategoryDto(Feedback_uploading feedbackUploading) {
        categoryDto dto = new categoryDto();
        dto.setId(feedbackUploading.getId());
        dto.setName(feedbackUploading.getName());
        dto.setText(feedbackUploading.getText());
        dto.setReturnedImg(feedbackUploading.getImage()); // Set the image bytes for the DTO
        return dto;
    }


    //deleting the events

    public categoryDto deletingevent(int id) {
        // Fetch the existing entity from the database
        Feedback_uploading feedbackUploading = feedback.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Feedback not found with id: " + id));

        // Delete the entity
        feedback.delete(feedbackUploading);

        // Convert the deleted entity to DTO for response (if needed)
        return convertToCategoryDto(feedbackUploading);
    }


}
