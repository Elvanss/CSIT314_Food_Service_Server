package com.management.csit314_project.Mapper;

import com.management.csit314_project.DTO.AddressDTO;
import com.management.csit314_project.DTO.CustomerAddressDTO;
import com.management.csit314_project.DTO.RestaurantAddressDTO;
import com.management.csit314_project.DTO.RestaurantDTO;
import com.management.csit314_project.DTO.UserDTO.UserDTO;
import com.management.csit314_project.Mapper.UserMapper.UserMapper;
import com.management.csit314_project.Model.Address;
import com.management.csit314_project.Model.CustomerAddress;
import com.management.csit314_project.Model.RestaurantAddress;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class AddressMapper implements Converter<Address, AddressDTO> {

    private final UserMapper userMapper;
    private final AddressMapper addressMapper;
    private final RestaurantMapper restaurantMapper;

    public AddressMapper(UserMapper userMapper, AddressMapper addressMapper, RestaurantMapper restaurantMapper) {
        this.userMapper = userMapper;
        this.addressMapper = addressMapper;
        this.restaurantMapper = restaurantMapper;
    }

    public AddressDTO convert(Address address) {
        return mapAddress(address);
    }

    public CustomerAddressDTO convertCustomerAddress(CustomerAddress customerAddress) {
        return mapCustomerAddress(customerAddress);
    }

    public RestaurantAddressDTO convertRestaurantAddress(RestaurantAddress restaurantAddress) {
        return mapRestaurantAddress(restaurantAddress);
    }

    private AddressDTO mapAddress(Address address) {
        return new AddressDTO(address.getId(),
                address.getBname(),
                address.getStreet(),
                address.getSuburb(),
                address.getState(),
                address.getPostCode());
    }

    public CustomerAddressDTO mapCustomerAddress(CustomerAddress customerAddress) {
        if (customerAddress == null) {
            throw new RuntimeException("There is no user address available!");
        }

        UserDTO userDTO = userMapper.convert(customerAddress.getUserId());
        AddressDTO addressDTO = addressMapper.convert(customerAddress.getAddressId());
        return new CustomerAddressDTO(customerAddress.getId(),
                                        userDTO,
                                        addressDTO);
    }

    public RestaurantAddressDTO mapRestaurantAddress(RestaurantAddress restaurantAddress) {
        if (restaurantAddress == null) {
            throw new RuntimeException("There is no restaurant address available!");
        }

        RestaurantDTO restaurantDTO = restaurantMapper.convert(restaurantAddress.getRestaurantId());
        AddressDTO addressDTO = addressMapper.convert(restaurantAddress.getAddressId());
        return new RestaurantAddressDTO(restaurantAddress.getId(),
                                        restaurantDTO,
                                        addressDTO);
    }

    //    private AddressDTO mapCustomerAddress(CustomerAddress customerAddress) {
//        return new AddressDTO(customerAddress.getId(),
//                customerAddress.getUserId() != null
//                        ? userMapper.convert(customerAddress.getUserId())
//                        : null,
//                mapAddress(customerAddress));
//    }
}
