package com.management.csit314_project.Repository;

import com.management.csit314_project.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<Item, Integer> {
}
