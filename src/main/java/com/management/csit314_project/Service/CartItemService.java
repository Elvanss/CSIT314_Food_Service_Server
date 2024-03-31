package com.management.csit314_project.Service;

import com.management.csit314_project.DTO.CartItemResDTO;
import com.management.csit314_project.Mapper.CartItemMapper;
import com.management.csit314_project.Model.Cart;
import com.management.csit314_project.Model.CartItem;
import com.management.csit314_project.Model.Item;
import com.management.csit314_project.Model.User.User;
import com.management.csit314_project.Repository.CartItemRepository;
import com.management.csit314_project.Repository.CartRepository;
import com.management.csit314_project.Repository.ItemRepository;
import com.management.csit314_project.Security.CustomUserDetails;
import com.management.csit314_project.System.Exception.AppException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Date;


@Service
@Slf4j
public class CartItemService {

    private final CartRepository cartRepository;
    private final ItemRepository itemRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartItemMapper;

    public CartItemService(CartRepository cartRepository,
                           ItemRepository itemRepository,
                           CartItemRepository cartItemRepository,
                           CartItemMapper cartItemMapper) {

        this.cartRepository = cartRepository;
        this.itemRepository = itemRepository;
        this.cartItemRepository = cartItemRepository;
        this.cartItemMapper = cartItemMapper;
    }

    // Create a new cart item
    public CartItemResDTO createCartItem(CartItemResDTO itemInCartDTO) {
        Item item = getItemById(itemInCartDTO.getId());
        log.info("Product: " + item.getId());

        // Check if the cart item is already in the cart
        Optional<CartItem> foundCartItem = cartItemRepository.findByCartIdAndItemId(itemInCartDTO.getId(), item.getId());
        if (foundCartItem.isPresent()) {
            log.warn("Found cart item in cart");
            CartItem cartItem = foundCartItem.get();
            cartItem.setQuantity(cartItem.getQuantity() + itemInCartDTO.getQuantity());
            cartItem.setDateTime((java.sql.Date) new Date());
            CartItem savedCartItem = cartItemRepository.save(cartItem);
            updateCartTotalPrice(savedCartItem, true);
            return cartItemMapper.convert(savedCartItem);
        }

        CartItem cartItem = new CartItem();
        log.warn("Create new cart item");
        cartItem.setItem(item);
        cartItem.setQuantity(itemInCartDTO.getQuantity());
        cartItem.setDateTime((java.sql.Date) new Date());
        CartItem savedCartItem = cartItemRepository.save(cartItem);
        updateCartTotalPrice(savedCartItem, true);
        return cartItemMapper.convert(savedCartItem);
    }

    // Get all cart items in the cart
    public CartItem deleteAfterOrder(Integer id, Integer orderId) {
        Optional<CartItem> found = cartItemRepository.findById(id);
        if (found.isEmpty()){
            log.error("CartItem with id " + id + " is not found");
            throw new AppException(HttpStatus.NOT_FOUND.value(), "CartItem not found");
        }

        CartItem cartItem = found.get();
        cartItemRepository.delete(cartItem);
        updateCartTotalPrice(cartItem, false);
        return cartItem;
    }

    // Get all cart items in the cart
    private User getUserByContextHolder(){
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder
                .getContext()
                .getAuthentication().getPrincipal();
        return userDetails.getUser();
    }

    // Get all cart items in the cart
    private Cart getCartById(Integer id){
        Optional<Cart> foundCart = cartRepository.findById(id);
        return foundCart.orElse(null);
    }

    // Get all cart items in the cart
    private Item getItemById(Integer id){
        Optional<Item> foundItem = itemRepository.findById(id);
        if (foundItem.isEmpty()) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), "Item not found");
        }
        return foundItem.get();
    }

    // Get all cart items in the cart
    private void updateCartTotalPrice(CartItem cartItem, boolean isAdding) {
        Cart cart = getCartById(cartItem.getItem().getId());
        Long itemTotalPrice = cartItem.getItem().getPrice() * cartItem.getQuantity();
        if (isAdding) {
            cart.setTotalPrice(cart.getTotalPrice() + itemTotalPrice);
        } else {
            cart.setTotalPrice(cart.getTotalPrice() - itemTotalPrice);
        }
        cartRepository.save(cart);
    }

}