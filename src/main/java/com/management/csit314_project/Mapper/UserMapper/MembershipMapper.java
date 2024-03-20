package com.management.csit314_project.Mapper.UserMapper;

import com.management.csit314_project.DTO.UserDTO.CategoryDTO.MembershipUserDTO;
import com.management.csit314_project.Model.User.Category.MembershipUser;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MembershipMapper implements Converter<MembershipUser, MembershipUserDTO> {

    @Override
    public MembershipUserDTO convert(MembershipUser membershipUser) {
        return new MembershipUserDTO(membershipUser.getMemId(),
                                        membershipUser.getExpiryDateTime(),
                                        membershipUser.getMembershipType());
    }
}

