package com.management.csit314_project.Repository;

import com.management.csit314_project.Model.RestaurantAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RestaurantAddressRepository extends JpaRepository<RestaurantAddress, Integer> {

    @Query("SELECT a FROM RestaurantAddress a WHERE a.restaurantId = ?1")
    Optional<RestaurantAddress> findByRestaurantId(Integer restaurantId);
}
