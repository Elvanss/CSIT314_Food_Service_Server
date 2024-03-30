package com.management.csit314_project.Repository;

import com.management.csit314_project.Model.User.UserRoles;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRoleRepository extends JpaRepository<UserRoles, Integer> {

//    @Query("SELECT ur FROM UserRoles ur WHERE ur.role = ?1")
//    List<UserRoles> findByRoleId(Integer roleId);

    @Query("SELECT ur FROM UserRoles ur WHERE ur.user = ?1")
    List<UserRoles> findByUserId(Integer userId);
}
