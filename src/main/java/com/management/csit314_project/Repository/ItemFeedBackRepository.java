package com.management.csit314_project.Repository;

import com.management.csit314_project.Model.ItemFeedBack;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemFeedBackRepository extends JpaRepository<ItemFeedBack, Integer> {
}
