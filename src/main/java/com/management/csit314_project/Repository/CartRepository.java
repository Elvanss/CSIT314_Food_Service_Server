package com.management.csit314_project.Repository;

import com.management.csit314_project.Model.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartRepository extends JpaRepository<Cart, Integer> {
}
