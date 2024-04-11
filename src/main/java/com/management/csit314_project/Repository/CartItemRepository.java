package com.management.csit314_project.Repository;

import com.management.csit314_project.Model.CartItem;
import com.management.csit314_project.Model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Integer> {

    // Find all cart items by cart id
    @Query("SELECT c FROM CartItem c WHERE c.item.id = ?1")
    List<CartItem> findAllByCartId(Integer cartId);

    @Query("SELECT c FROM CartItem c WHERE c.item.id = ?1")
    Optional<CartItem> findByCartIdAndItemId(Integer cartItemId, Integer itemId);

//    @Query("SELECT c FROM CartItem c WHERE c.item.id = ?1")
//    List<CartItem> findAllByOrderId(Long orderId);
}
