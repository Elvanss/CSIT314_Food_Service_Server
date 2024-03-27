package com.management.csit314_project.Service.UserSer;

import com.management.csit314_project.DTO.UserDTO.UserDTO;
import com.management.csit314_project.Mapper.UserMapper.UserMapper;
import com.management.csit314_project.Model.User.User;
import com.management.csit314_project.Repository.CartRepository;
import com.management.csit314_project.Repository.UserRepo.MembershipRepository;
import com.management.csit314_project.Repository.UserRepo.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final MembershipRepository membershipUser;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final CartRepository cartRepository;

    public UserService(MembershipRepository membershipUser,
                       UserRepository userRepository,
                       UserMapper userMapper,
                       CartRepository cartRepository) {
        //Assigned
        this.membershipUser = membershipUser;
        this.userMapper = userMapper;
        this.cartRepository = cartRepository;
        this.userRepository = userRepository;
    }

    // Show the userInfo by username
    public UserDTO getUserInfo(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            //get all variables from userDTO
            User user = userOptional.get();
            return userMapper.convert(user);
        } else {
            throw new UsernameNotFoundException("User is not found!");
        }
    }

    // Update User Profile
    public UserDTO updateUserProfile(String username, UserDTO updateFields) {
        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User is not found! "));

        if (updateFields.getEmail() != null) {
            existingUser.setEmail(updateFields.getEmail());
        }

        if (updateFields.getFirstName() != null && updateFields.getLastName() != null) {
            existingUser.setFirstName(updateFields.getFirstName());
            existingUser.setLastName(updateFields.getLastName());
        }
        //Save the current user to repo
        User updatedUser = userRepository.save(existingUser);

        return userMapper.convert(updatedUser);
    }

    //Delete the usre account
    public void deleteUser(String username) {
        Optional<User> userAccount = userRepository.findByUsername(username);
        if (username == null ) {
            throw new RuntimeException("User not found with id: " + userAccount);
        }
        userRepository.delete(userAccount.get());
    }



}

