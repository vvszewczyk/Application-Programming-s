package org.pau.springbootapplication;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.pau.springbootapplication.Controllers.RatingController;
import org.pau.springbootapplication.MainSoruce.Rating;
import org.pau.springbootapplication.Services.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(RatingController.class)
public class RatingControllerTest
{
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RatingService ratingService;

    private Rating rating;

    @BeforeEach
    void setUp()
    {
        MockitoAnnotations.openMocks(this);
        rating = new Rating(5, null, "Great car!");
    }

    @Test
    public void addRating_ShouldReturnCreatedStatus() throws Exception
    {
        doNothing().when(ratingService).addRating(any(Rating.class));
        mockMvc.perform(post("/api/rating")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"rating\": 5, \"comment\": \"Great car!\", \"vehicle\": {\"id\": 19}}"))
                .andExpect(status().isCreated());
    }
}
