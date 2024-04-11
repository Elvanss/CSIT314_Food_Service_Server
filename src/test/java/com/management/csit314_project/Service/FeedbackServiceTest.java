package com.management.csit314_project.Service;

import com.management.csit314_project.Model.FeedBack;
import com.management.csit314_project.Model.Restaurant;
import com.management.csit314_project.Model.Type.Rating;
import com.management.csit314_project.Model.User.User;
import com.management.csit314_project.Repository.FeedbackRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class FeedbackServiceTest {

    @Mock
    FeedbackRepository feedbackRepository;

    @InjectMocks
    FeedbackService feedbackService;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void findByIdSuccess() {

        User user = new User();
        Restaurant restaurant = new Restaurant();

        FeedBack feedBack = new FeedBack(123456, user, restaurant, "Nhu cak", Rating.OneStar, LocalDateTime.now());

        given(feedbackRepository.findById(123456)).willReturn(Optional.of(feedBack));

        FeedBack returnedFeedback = feedbackService.findById(123456);

        assertThat(returnedFeedback.getId()).isEqualTo(feedBack.getId());
        assertThat(returnedFeedback.getUser()).isEqualTo(feedBack.getUser());
        assertThat(returnedFeedback.getRestaurant()).isEqualTo(feedBack.getRestaurant());
        assertThat(returnedFeedback.getContent()).isEqualTo(feedBack.getContent());
        assertThat(returnedFeedback.getRating()).isEqualTo(feedBack.getRating());
        assertThat(returnedFeedback.getPostDateTime()).isEqualTo(feedBack.getPostDateTime());
        verify(feedbackRepository, times(1)).findById(123456);

    }

    @Test
    void saveFeedback() {
    }

    @Test
    void getFeedbackByCustomer() {
    }

    @Test
    void getFeedbackByRestaurant() {
    }
}