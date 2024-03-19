package com.management.csit314_project.Repository;

import com.management.csit314_project.Model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

//    @Query
//    User findUserByEmail(User user);

}
