package com.management.csit314_project.Service.UserSer;

import com.management.csit314_project.DTO.LoginDTO;
import com.management.csit314_project.DTO.LoginResponseDTO;
import com.management.csit314_project.DTO.UserDTO.UserDTO;
import com.management.csit314_project.Mapper.UserMapper.UserMapper;
import com.management.csit314_project.Model.Cart;
import com.management.csit314_project.Model.CartItem;
import com.management.csit314_project.Model.Type.MembershipType;
import com.management.csit314_project.Model.User.Category.MembershipUser;
import com.management.csit314_project.Model.User.Role;
import com.management.csit314_project.Model.User.User;
import com.management.csit314_project.Model.User.UserRoles;
import com.management.csit314_project.Repository.CartItemRepository;
import com.management.csit314_project.Repository.CartRepository;
import com.management.csit314_project.Repository.RoleRepository;
import com.management.csit314_project.Repository.UserRepo.MembershipRepository;
import com.management.csit314_project.Repository.UserRepo.UserRepository;
import com.management.csit314_project.Repository.UserRoleRepository;
import com.management.csit314_project.Security.CustomUserDetails;
import com.management.csit314_project.Security.JwtGenerated;
import com.management.csit314_project.Security.JwtTokenProvider;
import com.management.csit314_project.System.Exception.AppException;
import jakarta.persistence.metamodel.IdentifiableType;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class UserService {

    private final MembershipRepository membershipRepository;
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserRoleRepository userRolesRepository;
    private final CartRepository cartRepository;
    private final PasswordEncoder passwordEncoder;
    private final CartItemRepository cartItemRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationManager authenticationManager;

    public UserService(MembershipRepository membershipRepository,
                       UserRepository userRepository,
                       UserMapper userMapper,
                       UserRoleRepository userRolesRepository,
                       CartRepository cartRepository,
                       CartItemRepository cartItemRepository,
                       PasswordEncoder passwordEncoder,
                       RoleRepository roleRepository,
                       JwtTokenProvider jwtTokenProvider,
                       AuthenticationManager authenticationManager) {

        this.membershipRepository = membershipRepository;
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.userRolesRepository = userRolesRepository;
        this.cartRepository = cartRepository;
        this.cartItemRepository = cartItemRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
        this.jwtTokenProvider = jwtTokenProvider;
        this.authenticationManager = authenticationManager;
    }

    @Transactional
    public UserDTO register(UserDTO newUser) {

        if (userRepository.existsByUsername(newUser.getUserName())) {
            throw new AppException(HttpStatus.BAD_REQUEST.value(), "Username is already taken!");
        }

        // add check for email exists in DB
        if (userRepository.existsByEmail(newUser.getEmail())) {
            throw new AppException(HttpStatus.BAD_REQUEST.value(), "Email is already taken!");
        }
        User user = new User();
        user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        user.setUserName(newUser.getUserName());
        user.setFirstName(newUser.getFirstName());
        user.setLastName(newUser.getLastName());
        user.setEmail(newUser.getEmail());
        User savedUser = userRepository.save(user);


        // Assign the "USER" role
        Role roleEntity = roleRepository.findByName(com.management.csit314_project.Model.Type.Roles.USER.name()).orElseThrow();
        Role userRole = roleRepository.findByName("USER")
                .orElseThrow(() -> new AppException(HttpStatus.INTERNAL_SERVER_ERROR.value(), "Role not found!"));
//        user.setRoleUsers(Collections.singleton(userRole.getName()));
        //user.setRoles(Set.of(String.valueOf(roleEntity)));
//        UserRoles roleUser = new UserRoles();
//        roleUser.setUserId(savedUser.getId());
//        roleUser.setRoleId(roleEntity.getId());
//        roleUserRepository.save(roleUser);
//
//        Cart cart = new Cart();
//        cart.setUserId(savedUser.getId());
//        LocalDate localDate = LocalDate.now();
//        cart.setCreatedDate(Date.valueOf(localDate));
//        cartRepository.save(cart);
//        // Save the user to the database
//        return userMapper.userToUserDto(savedUser);
        return null;
    }

    @Transactional
    public LoginResponseDTO login(LoginDTO loginDTO) {
        try {
            // Perform authentication
            log.info("Before login");
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDTO.getUsernameOrEmail(),
                                                            loginDTO.getPassword()));
            log.info("After login");
            log.info("Login with username: " + loginDTO.getUsernameOrEmail() + " and password: " + loginDTO.getPassword());

            // Retrieve user details from the authenticated token
            CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();

            // Generate JWT token
            JwtGenerated jwtGenerated = jwtTokenProvider.generatedToken(userDetails);

            // Get user id
            Integer userId = userDetails.getUser().getId();
            //get Cart Id by userId
            Integer cartId = getCartByUserId(userId).getId();
            int quantity = getQuantityByUserId(userId);

            //get role by user id
            List<String> roles = getRoleListByUserId(userDetails.getUser().getId());

            // Build the response DTO
            return LoginResponseDTO.builder()
                    .accessToken(jwtGenerated.getAccessToken())
                    .expiredIn(jwtGenerated.getExpiredIn())
                    .roles(userDetails.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .collect(Collectors.toList()))
                    .cartId(cartId)
                    .userId(userId) // user id is the same as the cart id
                    .quantity(quantity)
                    .roles(roles)
                    .build();
        } catch (Exception e) {
            // Handle authentication failure
            log.error(e.getMessage(), e);
        }
        return null;
    }

    // Get the quantity of items in the cart by user id
    private int getQuantityByUserId(Integer userId) {
        Cart cart = getCartByUserId(userId);
        List<CartItem> cartItems = cartItemRepository.findAllByCartId(cart.getId());
        return cartItems.size();
    }

    // Get the cart by user id (user id is the same as the cart id)
    private Cart getCartByUserId(Integer userId) {
        Optional<Cart> foundCart = cartRepository.findByUserId(userId);
        if (foundCart.isEmpty()) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), "Cart not found for user with id: " + userId);
        }
        return foundCart.get();
    }

    // Get the User Account
    public UserDTO getUserInfo(String username) {
        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return userMapper.convert(user);
        } else {
            throw new UsernameNotFoundException("User is not found!");
        }
    }

    // Update the user profile
    public UserDTO updateUserProfile(String username, UserDTO updateFields) {
        User existingUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User is not found!"));

        if (updateFields.getEmail() != null) {
            existingUser.setEmail(updateFields.getEmail());
        }

        if (updateFields.getFirstName() != null && updateFields.getLastName() != null) {
            existingUser.setFirstName(updateFields.getFirstName());
            existingUser.setLastName(updateFields.getLastName());
        }

        User updatedUser = userRepository.save(existingUser);

        return userMapper.convert(updatedUser);
    }


    // Delete the user account
    public void deleteUser(String username) {
        Optional<User> userAccount = userRepository.findByUsername(username);
        if (userAccount.isPresent()) {
            userRepository.delete(userAccount.get());
        } else {
            throw new UsernameNotFoundException("User not found with username: " + username);
        }
    }

    // Admin Functions
    public List<UserDTO> getAllUsers() {
        List<User> users = userRepository.findAll();
        return users.stream()
                .map(userMapper::convert)
                .collect(Collectors.toList());
    }

    // Get user by id
    public UserDTO getUserById(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(HttpStatus.NOT_FOUND.value(), "User with id " + userId + " does not exist"));
        return userMapper.convert(user);
    }

    // Add a new user
    public UserDTO addUser(UserDTO userDTO) {
        User user = userMapper.convertToEntity(userDTO);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User savedUser = userRepository.save(user);
        return userMapper.convert(savedUser);
    }

    // Other functions
    private List<String> getRoleListByUserId(Integer userId) {
        List<UserRoles> listRoleUser = userRolesRepository.findByUserId(userId);
        List<String> list = listRoleUser
                .stream()
                .map(roleUser -> getRoleById(roleUser.getId())).collect(Collectors.toList());
        return list;
    }

    private String getRoleById(Integer roleId) {
        Optional<Role> role = roleRepository.findById(roleId);
        if (role.isEmpty()) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), "Role with id " + roleId + " does not exist");
        }

        return role.get().getName();
    }

    // Sign up for membership
    @Transactional
    public void signUpForMembership(int userId, MembershipType membershipType) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));

        MembershipUser membership = new MembershipUser();
        membership.setMemId(userId);
        membership.setMembershipType(membershipType);
        Timestamp expiryDateTime = calculateExpiryDate(membershipType);
        if (membershipRepository.existsByMemId(userId)) {
            throw new AppException(HttpStatus.BAD_REQUEST.value(), "User already has a membership");
        }

        /* Check if the membership type is valid
            * set the expiry date by adding 1 month or 1 year to the current date
            * return an error if the membership type is invalid
            * return an error if the user already has a membership
            * save the membership to the database
        * */
        if (membershipType == MembershipType.MONTHLY) {
            membership.setMembershipType(MembershipType.MONTHLY);
        } else if (membershipType == MembershipType.ANNUALLY) {
            membership.setMembershipType(MembershipType.ANNUALLY);
        } else {
            throw new AppException(HttpStatus.BAD_REQUEST.value(), "Invalid membership type: " + membershipType);
        }
        membership.setExpiryDateTime(expiryDateTime);

        user.setMembership(membership);
        userRepository.save(user);
    }

    // Calculate the expiry date based on the membership type
    private Timestamp calculateExpiryDate(MembershipType membershipType) {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime expiryDateTime = switch (membershipType) {
            case MONTHLY -> now.plusMonths(1);
            case ANNUALLY -> now.plusYears(1);
            default -> throw new IllegalArgumentException("Invalid membership type: " + membershipType);
        };

        return Timestamp.valueOf(expiryDateTime);
    }

    @Scheduled(cron = "0 0 0 * * ?")
    public void removeExpiredMemberships() {
        List<User> users = userRepository.findAll();
        for (User user : users) {
            MembershipUser membership = user.getMembership();
            if (membership != null && membership
                                        .getExpiryDateTime()
                                        .before(new Timestamp(System.currentTimeMillis()))) {

                user.setMembership(null);
                userRepository.save(user);
            }
        }
    }

//    public void updateMembership(int userId, MembershipType membershipType) {
//        User user = userRepository.findById(userId)
//                .orElseThrow(() -> new UsernameNotFoundException("User not found with id: " + userId));
//
//        MembershipUser membership = user.getMembership();
//        if (membership == null) {
//            throw new AppException(HttpStatus.BAD_REQUEST.value(), "User does not have a membership");
//        }
//
//        membership.setMembershipType(membershipType);
//        Timestamp expiryDateTime = calculateExpiryDate(membershipType);
//        membership.setExpiryDateTime(expiryDateTime);
//
//        user.setMembership(membership);
//        userRepository.save(user);
//    }
}