package com.management.csit314_project.Repository;

import com.management.csit314_project.Model.RestaurantAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantAddressRepository extends JpaRepository<RestaurantAddress, Integer> {


}
