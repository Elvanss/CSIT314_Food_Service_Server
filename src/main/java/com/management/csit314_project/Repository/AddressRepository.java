package com.management.csit314_project.Repository;

import com.management.csit314_project.Model.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

//    Optional<Address> findByUserId(Long userId);
//
//    List<Address> findAllByUserId(Long userId);

}
