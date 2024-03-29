package com.management.csit314_project.Repository.UserRepo;

import com.management.csit314_project.Model.User.Category.MembershipUser;
import com.management.csit314_project.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembershipRepository extends JpaRepository<MembershipUser, Integer> {
}
