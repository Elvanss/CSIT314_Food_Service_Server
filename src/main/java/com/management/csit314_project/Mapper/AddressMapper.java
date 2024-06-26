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
import org.springframework.context.annotation.Lazy;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;


@Component
public class AddressMapper implements Converter<Address, AddressDTO> {

    private final UserMapper userMapper;
    private final RestaurantMapper restaurantMapper;

    public AddressMapper(UserMapper userMapper,
                         @Lazy RestaurantMapper restaurantMapper) {
        this.userMapper = userMapper;
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


    /*
     * Convert Address to AddressDTO
     * */
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
        AddressDTO addressDTO = this.convert(customerAddress.getAddressId());
        return new CustomerAddressDTO(customerAddress.getId(),
                userDTO,
                addressDTO);
    }

    public RestaurantAddressDTO mapRestaurantAddress(RestaurantAddress restaurantAddress) {
        if (restaurantAddress == null) {
            throw new RuntimeException("There is no restaurant address available!");
        }

        RestaurantDTO restaurantDTO = restaurantMapper.convert(restaurantAddress.getRestaurantId());
        AddressDTO addressDTO = this.convert(restaurantAddress.getAddressId());
        return new RestaurantAddressDTO(restaurantAddress.getId(),
                restaurantDTO,
                addressDTO);
    }

    /* Convert to Entity */

    // Convert AddressDTO to Address Entity
    public Address convertAddressToEntity(AddressDTO addressDTO) {
        Address address = new Address();
        address.setId(addressDTO.getId());
        address.setBname(addressDTO.getBname());
        address.setStreet(addressDTO.getStreet());
        address.setSuburb(addressDTO.getSuburb());
        address.setState(addressDTO.getState());
        address.setPostCode(addressDTO.getPostCode());
        return address;
    }

    // Convert CustomerAddressDTO to CustomerAddress Entity
    public CustomerAddress convertCustomerAddressToEntity(CustomerAddressDTO customerAddressDTO) {
        CustomerAddress customerAddress = new CustomerAddress();
        customerAddress.setId(customerAddressDTO.getId());
        customerAddress.setUserId(userMapper.convertToEntity(customerAddressDTO.getUserDTO()));
        customerAddress.setAddressId(convertAddressToEntity(customerAddressDTO.getAddressDTO()));
        return customerAddress;
    }

    // Convert RestaurantAddressDTO to RestaurantAddress Entity
    public RestaurantAddress convertRestaurantAddressToEntity(RestaurantAddressDTO restaurantAddressDTO) {
        RestaurantAddress restaurantAddress = new RestaurantAddress();
        restaurantAddress.setId(restaurantAddressDTO.getId());
        restaurantAddress.setRestaurantId(restaurantMapper.convertToEntity(restaurantAddressDTO.getRestaurantDTO()));
        restaurantAddress.setAddressId(convertAddressToEntity(restaurantAddressDTO.getAddressDTO()));
        return restaurantAddress;
    }


}
