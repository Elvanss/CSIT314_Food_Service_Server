package com.management.csit314_project.Mapper;

import com.management.csit314_project.DTO.AddressDTO;
import com.management.csit314_project.DTO.UserDTO.UserDTO;
import com.management.csit314_project.Mapper.UserMapper.UserMapper;
import com.management.csit314_project.Model.CustomerAddress;
import com.management.csit314_project.DTO.CustomerAddressDTO;
import org.springframework.core.convert.converter.Converter;

public class CustomerAddressMapper implements Converter<CustomerAddress, CustomerAddressDTO> {

    private final UserMapper userMapper;
    private final AddressMapper addressMapper;

    public CustomerAddressMapper(UserMapper userMapper, AddressMapper addressMapper) {
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
    }

    public CustomerAddressDTO convert(CustomerAddress customerAddress) {
        if (customerAddress == null) {
            return null;
        }

        UserDTO userDTO = userMapper.convert(customerAddress.getUserId());
        AddressDTO addressDTO = addressMapper.convert(customerAddress.getAddressId());

        return new CustomerAddressDTO(customerAddress.getId(), userDTO, addressDTO);
    }
}

