package com.management.csit314_project.Mapper.UserMapper;

import com.management.csit314_project.DTO.UserDTO.UserDTO;
import com.management.csit314_project.Model.User.User;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class UserMapper {

    private final MembershipMapper membershipMapper;

    public UserMapper(MembershipMapper membershipMapper) {
        this.membershipMapper = membershipMapper;
    }

    public UserDTO convert(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setFirstName(user.getFirstName());
        userDTO.setLastName(user.getLastName());
        userDTO.setUserName(user.getUserName());
        userDTO.setPassword(user.getPassword());
        userDTO.setEmail(user.getEmail());
        userDTO.setPhoneNumber(user.getPhoneNumber());
        userDTO.setMember(user.isMember());

        // Map MembershipUser
        if (user.getMembership() != null) {
            userDTO.setMembershipUserDTO(membershipMapper.convert(user.getMembership()));
        }

        return userDTO;
    }

    public User convertToEntity(UserDTO userDTO) {
        User user = new User();
        user.setId(userDTO.getId());
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setUserName(userDTO.getUserName());
        user.setEmail(userDTO.getEmail());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        user.setMember(userDTO.isMember());

        // Map MembershipUser
        if (userDTO.getMembershipUserDTO() != null) {
            user.setMembership(membershipMapper.convertToEntity(userDTO.getMembershipUserDTO()));
        }

        return user;
    }


}