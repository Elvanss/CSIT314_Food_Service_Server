package com.management.csit314_project.Repository;

import com.management.csit314_project.Model.CustomerAddress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerAddressRepository extends JpaRepository<CustomerAddress, Integer> {

    @Query("SELECT a FROM CustomerAddress a WHERE a.userId = ?1")
    Optional<CustomerAddress> findByUserId(Integer userId);
}
