package com.management.csit314_project.Repository.UserRepo.CategoryRepo;

import com.management.csit314_project.Model.User.Category.NormalUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NormalUserRepository extends JpaRepository<NormalUser, Integer> {
}
