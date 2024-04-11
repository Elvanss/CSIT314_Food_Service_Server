package com.management.csit314_project.Repository;

import com.management.csit314_project.Model.FeedBack;
import com.management.csit314_project.Model.Restaurant;
import com.management.csit314_project.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FeedbackRepository extends JpaRepository<FeedBack, Integer> {
    List<FeedBack> findByUser(User user);
    List<FeedBack> findByRestaurant(Restaurant restaurant);
}
