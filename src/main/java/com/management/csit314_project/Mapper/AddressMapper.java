package com.management.csit314_project.Mapper;

import com.management.csit314_project.DTO.AddressDTO;
import com.management.csit314_project.Mapper.UserMapper.UserMapper;
import com.management.csit314_project.Model.Address;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements Converter<Address, AddressDTO> {

    @Override
    public AddressDTO convert(Address address) {
        return new AddressDTO(address.getId(),
                                address.getBname(),
                                address.getStreet(),
                                address.getSuburb(),
                                address.getState(),
                                address.getPostCode());
    }
}
