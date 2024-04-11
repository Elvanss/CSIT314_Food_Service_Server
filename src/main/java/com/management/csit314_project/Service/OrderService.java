package com.management.csit314_project.Service;

import com.management.csit314_project.DTO.CartItemResDTO;
import com.management.csit314_project.DTO.OrderDTO;
import com.management.csit314_project.Mapper.CartItemMapper;
import com.management.csit314_project.Mapper.OrderMapper;
import com.management.csit314_project.Model.Address;
import com.management.csit314_project.Model.Cart;
import com.management.csit314_project.Model.CartItem;
import com.management.csit314_project.Model.Order;
import com.management.csit314_project.Model.Type.OrderStatus;
import com.management.csit314_project.Repository.AddressRepository;
import com.management.csit314_project.Repository.CartItemRepository;
import com.management.csit314_project.Repository.CartRepository;
import com.management.csit314_project.Repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
@Slf4j
public class OrderService {

//    private final OrderRepository orderRepository;
//    private final AddressRepository addressRepository;
//    private final CartRepository cartRepository;
//    private final CartItemRepository cartLineItemRepository;
//    private final OrderMapper orderMapper;
//    private final CartItemService cartLineItemService;
//    private final CartItemMapper cartLineItemMapper;
//
//    public OrderService(OrderRepository orderRepository, AddressRepository addressRepository, CartRepository cartRepository, CartItemRepository cartLineItemRepository, OrderMapper orderMapper, CartItemService cartLineItemService, CartItemMapper cartLineItemMapper) {
//        this.orderRepository = orderRepository;
//        this.addressRepository = addressRepository;
//        this.cartRepository = cartRepository;
//        this.cartLineItemRepository = cartLineItemRepository;
//        this.orderMapper = orderMapper;
//        this.cartLineItemService = cartLineItemService;
//        this.cartLineItemMapper = cartLineItemMapper;
//    }
//
//    public OrderDTO create(Long userId) {
////        User user = getUserByContextHolder();
////        Long userId = user.getId();
//        Address address = getAddressByUserId(userId);
//        String addressString = address.getStreet() + ", " + address.getSuburb();
//
//        Cart cart = getCartByUserId(userId);
//        // get cart line item that not deleted
//        List<CartItem> listItems = getListCartLineItemsByCartId(cart.getId()).stream().filter(lineItem -> !lineItem.isDeleted()).collect(Collectors.toList());
//
//        Double totalPrice = getTotalPrice(listItems);
//
//        Order order = new Order();
//        order.setAddress(addressString);
//        order.setCreatedAt(new Date());
//        order.setStatus(OrderStatus.CREATED.name());
//        order.setTotalPrice(totalPrice);
//        order.setUserId(userId);
//
//
//
//        //Delete cart items after order has been created
//        List<CartItem> listCartItemOrdered = listItems.stream()
//                                                        .map(item -> cartLineItemService.deleteAfterOrder(item.getId(),
//                                                                                                            order.getId())).collect(Collectors.toList());
//        List<CartItemResDTO> cartItemResDTO = cartLineItemMapper.listCartItemsToListCartItems(listCartItemOrdered);
//
//        Order res = orderRepository.save(order);
//        return orderMapper.orderToOrderResDTO(res, cartItemResDTO);
//    }
//
//    public void changeStatus(Long id, String action) {
//        Order order = getOrderById(id);
//        switch (action.toLowerCase()) {
//            case "confirm"->order.setStatus(OrderStatus.DELIVERING.name());
//            case "paid" ->order.setStatus(OrderStatus.COMPLETED.name());
//            default -> {
//                log.error("Unknown action: " + action);
//                throw new AppException(HttpStatus.NOT_MODIFIED.value(), "Invalid action");
//            }
//        }
//        orderRepository.save(order);
//    }
//
//    public OrderResDTO findOne(Long id) {
//        Order order = getOrderById(id);
//        List<CartLineItem> listCartItemOrdered = getListCartLineItemsByOrderId(id);
//        List<CartItemResDTO> cartItemResDTO = cartLineItemMapper.listCartItemsToListCartItems(listCartItemOrdered);
//        return orderMapper.orderToOrderResDTO(order, cartItemResDTO);
//    }
//
//    public List<OrderResDTO> findByUserId(Long userId) {
//        List<Order> orderList = orderRepository.findByUserId(userId);
//        if (orderList.isEmpty()) {
//            return null;
//        }
//
//        return orderList.stream().map(order -> {
//            return findOne(order.getId());
//        }).collect(Collectors.toList());
//    }
//
//    public List<OrderResDTO> findAll() {
//        List<Order> orderList = orderRepository.findAll();
//        if (orderList.isEmpty()) {
//            throw new AppException(HttpStatus.NOT_FOUND.value(), "Order list is empty");
//        }
//        return orderList.stream().map(order ->  findOne(order.getId())).collect(Collectors.toList());
//    }
//
//
//    private Order getOrderById(Long id) {
//        Optional<Order> found = orderRepository.findById(id);
//        if (found.isEmpty()) {
//            log.error("Order id " + id + " not found");
//            throw new AppException(HttpStatus.NOT_FOUND.value(), "Order not found");
//        }
//        return found.get();
//    }
//
//    private User getUserByContextHolder(){
//        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        log.info("UserDetails: " + userDetails);
//        return userDetails.getUser();
//    }
//    private Address getAddressByUserId(Long userId) {
//        Optional<Address> foundAddress = addressRepository.findByUserId(userId);
//        if (foundAddress.isEmpty()){
//            log.error("User " + userId + " not found address, please create a new address");
//            throw new AppException(HttpStatus.NOT_FOUND.value(), "No address found for user, please create a new address");
//        }
//        return foundAddress.get();
//    }
//    private Cart getCartByUserId(Long userId){
//        Optional<Cart> foundCart = cartRepository.findByUserId(userId);
//        if (foundCart.isEmpty()){
//            throw new AppException(HttpStatus.NOT_FOUND.value(), "No cart found for user");
//        }
//        return foundCart.get();
//    }
//
//    private List<CartLineItem> getListCartLineItemsByCartId(Long cartId){
//        List<CartLineItem> listCartItems = cartLineItemRepository.findAllByCartId(cartId);
//        if (listCartItems.isEmpty()){
//            throw new AppException(HttpStatus.NO_CONTENT.value(), "Cart is empty");
//        }
//        return listCartItems;
//    }
//    private List<CartLineItem> getListCartLineItemsByOrderId(Long orderId) {
//        List<CartLineItem> listCartItems = cartLineItemRepository.findAllByOrderId(orderId);
//        if (listCartItems.isEmpty()){
//            return null;
////            throw new AppException(HttpStatus.NO_CONTENT.value(), "Order is empty");
//        }
//        return listCartItems;
//    }
//
//    private Double getTotalPrice(List<CartLineItem> list) {
//        return list.stream().mapToDouble(CartLineItem::getTotalPrice).sum();
//    }
}
