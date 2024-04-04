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
import org.hibernate.ObjectNotFoundException;
import org.springframework.context.annotation.Lazy;
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

    public AddressService(@Lazy AddressMapper addressMapper,
                          @Lazy AddressRepository addressRepository,
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
     * - Manage Restaurant Address
     * */

    /* Check All Generic Address */
    public List<AddressDTO> getAllAddresses() { //Both User and Restaurant (ADMIN side)
        List<Address> addresses = addressRepository.findAll();
        List<AddressDTO> addressDTOs = new ArrayList<>();
        for (Address address : addresses) {
            if (address == null) {
                throw new ObjectNotFoundException("The address List is empty!", address);
            }
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

    /* Manage User Address */
    // Add User Address
    public CustomerAddressDTO addUserAddress(CustomerAddressDTO customerAddressDTO) {
        CustomerAddress customerAddress = addressMapper.convertCustomerAddressToEntity(customerAddressDTO);
        customerAddressRepository.save(customerAddress);
        return addressMapper.convertCustomerAddress(customerAddress);
    }

    // Update User Address
    public CustomerAddressDTO updateUserAddress(CustomerAddressDTO customerAddressDTO) {
        CustomerAddress customerAddress = addressMapper.convertCustomerAddressToEntity(customerAddressDTO);
        customerAddressRepository.save(customerAddress);
        return addressMapper.convertCustomerAddress(customerAddress);
    }

    // Delete User Address
    public CustomerAddressDTO deleteUserAddress(Integer id) {
        CustomerAddress customerAddress = customerAddressRepository.findById(id).orElse(null);
        if (customerAddress == null) {
            throw new ObjectNotFoundException("The user address is not found!", customerAddress);
        }
        customerAddressRepository.delete(customerAddress);
        return addressMapper.convertCustomerAddress(customerAddress);
    }

    // Get User Address
    public CustomerAddressDTO getUserAddress(Integer id) {
        CustomerAddress customerAddress = customerAddressRepository.findById(id).orElse(null);
        if (customerAddress == null) {
            throw new ObjectNotFoundException("The user address is not found!", customerAddress);
        }
        return addressMapper.convertCustomerAddress(customerAddress);
    }

    // Get User Address by User Id
    public CustomerAddressDTO getUserAddressByUserId(Integer userId) {
        CustomerAddress customerAddress = customerAddressRepository.findByUserId(userId).orElse(null);
        if (customerAddress == null) {
            throw new ObjectNotFoundException("The user address is not found!", customerAddress);
        }
        return addressMapper.convertCustomerAddress(customerAddress);
    }


    /* Manage Restaurant Address */

    // Add Restaurant Address
    public RestaurantAddressDTO addRestaurantAddress(RestaurantAddressDTO restaurantAddressDTO) {
        RestaurantAddress restaurantAddress = addressMapper.convertRestaurantAddressToEntity(restaurantAddressDTO);
        restaurantAddressRepository.save(restaurantAddress);
        return addressMapper.convertRestaurantAddress(restaurantAddress);
    }

    // Update Restaurant Address
    public RestaurantAddressDTO updateRestaurantAddress(RestaurantAddressDTO restaurantAddressDTO) {
        RestaurantAddress restaurantAddress = addressMapper.convertRestaurantAddressToEntity(restaurantAddressDTO);
        restaurantAddressRepository.save(restaurantAddress);
        return addressMapper.convertRestaurantAddress(restaurantAddress);
    }

    // Delete Restaurant Address
    public RestaurantAddressDTO deleteRestaurantAddress(Integer id) {
        RestaurantAddress restaurantAddress = restaurantAddressRepository.findById(id).orElse(null);
        if (restaurantAddress == null) {
            throw new ObjectNotFoundException("The restaurant address is not found!", restaurantAddress);
        }
        restaurantAddressRepository.delete(restaurantAddress);
        return addressMapper.convertRestaurantAddress(restaurantAddress);
    }

    // Get Restaurant Address by Address Id
    public RestaurantAddressDTO getRestaurantAddress(Integer id) {
        RestaurantAddress restaurantAddress = restaurantAddressRepository.findById(id).orElse(null);
        if (restaurantAddress == null) {
            throw new ObjectNotFoundException("The restaurant address is not found!", restaurantAddress);
        }
        return addressMapper.convertRestaurantAddress(restaurantAddress);
    }

    // Get Restaurant Address by Restaurant Id
    public RestaurantAddressDTO getRestaurantAddressByRestaurantId(Integer restaurantId) {
        RestaurantAddress restaurantAddress = restaurantAddressRepository.findByRestaurantId(restaurantId).orElse(null);
        if (restaurantAddress == null) {
            throw new ObjectNotFoundException("The restaurant address is not found!", restaurantAddress);
        }
        return addressMapper.convertRestaurantAddress(restaurantAddress);
    }

}
