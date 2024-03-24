package com.management.csit314_project.Service;

import com.management.csit314_project.DTO.AddressDTO;
import com.management.csit314_project.DTO.CustomerAddressDTO;
import com.management.csit314_project.DTO.RestaurantAddressDTO;
import com.management.csit314_project.Mapper.AddressMapper;
import com.management.csit314_project.Model.Address;
import com.management.csit314_project.Model.CustomerAddress;
import com.management.csit314_project.Model.RestaurantAddress;
import com.management.csit314_project.Repository.AddressRepository;
import com.management.csit314_project.Repository.CustomerAddressRepository;
import com.management.csit314_project.Repository.RestaurantAddressRepository;
import com.management.csit314_project.Repository.RestaurantRepository;
import com.management.csit314_project.Repository.UserRepo.UserRepository;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Service
public class AddressService implements Serializable {

    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;
    private final UserRepository userRepository;
    private final RestaurantRepository restaurantRepository;
    private final CustomerAddressRepository customerAddressRepository;
    private final RestaurantAddressRepository restaurantAddressRepository;

    public AddressService(AddressMapper addressMapper,
                          AddressRepository addressRepository,
                          UserRepository userRepository,
                          RestaurantRepository restaurantRepository,
                          CustomerAddressRepository customerAddressRepository,
                          RestaurantAddressRepository restaurantAddressRepository) {

        this.addressMapper = addressMapper;
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
        this.restaurantRepository = restaurantRepository;
        this.customerAddressRepository = customerAddressRepository;
        this.restaurantAddressRepository = restaurantAddressRepository;
    }

    /*
     * Mutual Address Service (CRUD) In Order:
     * - Check All Generic Address
     * - Manage User Address
     * - Manage Address Address
     * */

    public List<AddressDTO> getAllAddresses() { //Both User and Restaurant (ADMIN side)
        List<Address> addresses = addressRepository.findAll();
        List<AddressDTO> addressDTOs = new ArrayList<>();
        for (Address address : addresses) {
            addressDTOs.add(addressMapper.convert(address));
        }

        return addressDTOs;
    }

    public List<CustomerAddressDTO> getAllUserAddresses() {
        List<CustomerAddress> customerAddresses = customerAddressRepository.findAll();
        List<CustomerAddressDTO> customerAddressDTOs = new ArrayList<>();

        for (CustomerAddress customerAddress : customerAddresses) {
            customerAddressDTOs.add(addressMapper.convertCustomerAddress(customerAddress));
        }

        return customerAddressDTOs;
    }

    public List<RestaurantAddressDTO> getAllRestaurantAddresses() {
        List<RestaurantAddress> restaurantAddresses = restaurantAddressRepository.findAll();
        List<RestaurantAddressDTO> restaurantAddressDTOs = new ArrayList<>();

        for (RestaurantAddress restaurantAddress : restaurantAddresses) {
            restaurantAddressDTOs.add(addressMapper.convertRestaurantAddress(restaurantAddress));
        }

        return restaurantAddressDTOs;
    }
}
