package com.management.csit314_project.Repository;

import com.management.csit314_project.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Item, Integer> {
}
