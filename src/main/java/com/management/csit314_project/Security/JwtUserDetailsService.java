package com.management.csit314_project.Security;

import com.management.csit314_project.Model.User.User;
import com.management.csit314_project.Repository.RoleRepository;
import com.management.csit314_project.Repository.UserRoleRepository;
import com.management.csit314_project.Repository.UserRepo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class JwtUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository roleUserRepository;
    private final RoleRepository roleRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user = userRepository.findByUsernameOrEmail(usernameOrEmail, usernameOrEmail)
                .orElseThrow(() -> new UsernameNotFoundException("User not exists by username or email"));

       return new CustomUserDetails(user, roleUserRepository, roleRepository);
    }

}
