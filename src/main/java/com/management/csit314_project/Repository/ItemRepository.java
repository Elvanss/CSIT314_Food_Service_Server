package com.management.csit314_project.Repository;

import com.management.csit314_project.Model.Item;
import com.management.csit314_project.Model.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("select i from Item i where i.restaurantId = ?1")
    List<Item> findByRestaurant(Restaurant restaurant);

    @Query("select i from Item i where i.id = ?1 and i.restaurantId = ?2")
    Optional<Item> findByIdAndRestaurantId(int itemId, Restaurant restaurant);
}
