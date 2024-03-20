package com.management.csit314_project.Mapper.UserMapper;

import com.management.csit314_project.DTO.UserDTO.UserDTO;
import com.management.csit314_project.Model.User.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserMapper implements Converter<User, UserDTO> {

    private final MembershipMapper membershipMapper;

    public UserMapper(MembershipMapper membershipMapper) {
        this.membershipMapper = membershipMapper;
    }

    //Convert from User to UserDTO
    @Override
    public UserDTO convert(User user) {
        return new UserDTO(user.getId(),
                            user.getFirstName(),
                            user.getLastName(),
                            user.getUserName(),
                            user.getEmail(),
                            user.getPhoneNumber(),
                            user.isMember(),
                            user.getMembership() != null
                                ? this.membershipMapper.convert(user.getMembership())
                                : null);
    }
}
