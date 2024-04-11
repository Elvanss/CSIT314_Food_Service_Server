package com.management.csit314_project.Service;

import com.management.csit314_project.Model.FeedBack;
import com.management.csit314_project.Model.Restaurant;
import com.management.csit314_project.Model.User.User;
import com.management.csit314_project.Repository.FeedbackRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    @Autowired
    public FeedbackService(FeedbackRepository feedbackRepository) {
        this.feedbackRepository = feedbackRepository;
    }

    public FeedBack findById(Integer feedbackId) {
        return this.feedbackRepository.findById(feedbackId).get();
    }

    public FeedBack saveFeedback(FeedBack feedback) {
        return feedbackRepository.save(feedback);
    }

    public List<FeedBack> getFeedbackByCustomer(User user) {
        return feedbackRepository.findByUser(user);
    }

    public List<FeedBack> getFeedbackByRestaurant(Restaurant restaurant) {
        return feedbackRepository.findByRestaurant(restaurant);
    }

}
