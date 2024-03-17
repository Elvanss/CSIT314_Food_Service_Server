package com.management.csit314_project.Repository.UserRepo.CategoryRepo;

import com.management.csit314_project.Model.User.Category.MembershipUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipUserRepository extends JpaRepository<MembershipUser, Integer> {
}
