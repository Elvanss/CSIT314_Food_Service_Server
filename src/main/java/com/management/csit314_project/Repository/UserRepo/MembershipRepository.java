package com.management.csit314_project.Repository.UserRepo;

import com.management.csit314_project.Model.User.Category.MembershipUser;
import com.management.csit314_project.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<MembershipUser, Integer> {

    // Find the membership user by user id
    @Query("SELECT m FROM MembershipUser m WHERE m.memId = ?1")
    boolean existsByMemId(int userId);
}
