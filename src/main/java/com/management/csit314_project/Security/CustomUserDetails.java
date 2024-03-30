package com.management.csit314_project.Security;

import com.management.csit314_project.Model.User.Role;
import com.management.csit314_project.Model.User.User;
import com.management.csit314_project.Model.User.UserRoles;
import com.management.csit314_project.Repository.RoleRepository;
import com.management.csit314_project.Repository.UserRoleRepository;
import com.management.csit314_project.System.Exception.AppException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CustomUserDetails implements UserDetails {
    private User user;
    private UserRoleRepository roleUserRepository;
    private RoleRepository roleRepository;
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<String> roles = getRoleListByUserId(user.getId());

        return roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role))
                .collect(Collectors.toList());

    }

    private List<String> getRoleListByUserId(Integer userId) {
        List<UserRoles> listRoleUser = roleUserRepository.findByUserId(userId);
        List<String> list = listRoleUser
                .stream()
                .map(roleUser -> getRoleById(roleUser.getId().longValue()))
                .collect(Collectors.toList());
        return list;
    }
    private String getRoleById(Long roleId) {
        Optional<Role> role = roleRepository.findById(roleId.intValue());
        if (role.isEmpty()) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), "Role with id " + roleId + " does not exist");
        }

        return role.get().getName();
    }

    @Override
    public String getPassword() {
        return user.getPassword();
    }

    @Override
    public String getUsername() {
        return user.getUserName();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
