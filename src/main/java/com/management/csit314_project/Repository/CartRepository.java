package com.management.csit314_project.Repository;

import com.management.csit314_project.Model.Cart;
import com.management.csit314_project.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartRepository extends JpaRepository<Cart, Integer> {

    // Find the cart by user id
    @Query("SELECT c FROM Cart c WHERE c.user.id = ?1")
    Optional<Cart> findByUserId(Long userId);

}
