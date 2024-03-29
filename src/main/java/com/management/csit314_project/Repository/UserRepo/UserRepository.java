package com.management.csit314_project.Repository.UserRepo;

import com.management.csit314_project.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

    // Find the user by username
    @Query("select u from User u where u.userName = ?1")
    Optional<User> findByUsername(String userName);

}
