package com.management.csit314_project.Repository;

import com.management.csit314_project.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order o WHERE o.userId = ?1")
    List<Order> findByUserId(Long userId);
}
