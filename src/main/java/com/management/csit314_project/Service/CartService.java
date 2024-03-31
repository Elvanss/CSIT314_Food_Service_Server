package com.management.csit314_project.Service;

import com.management.csit314_project.DTO.CartDTO;
import com.management.csit314_project.DTO.CartItemResDTO;
import com.management.csit314_project.Mapper.CartItemMapper;
import com.management.csit314_project.Mapper.CartMapper;
import com.management.csit314_project.Model.Cart;
import com.management.csit314_project.Model.CartItem;
import com.management.csit314_project.Repository.CartItemRepository;
import com.management.csit314_project.Repository.CartRepository;
import com.management.csit314_project.System.Exception.AppException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartService {

    // Repositories and Mappers
    private final CartRepository cartRepository;
    private final CartItemRepository cartItemRepository;
    private final CartItemMapper cartLineItemMapper;
    private final CartMapper cartMapper;

    // Constructor
    public CartService(CartRepository cartRepository,
                       CartItemRepository cartLineItemRepository,
                       CartItemMapper cartLineItemMapper,
                       CartMapper cartMapper) {

        this.cartRepository = cartRepository;
        this.cartItemRepository = cartLineItemRepository;
        this.cartLineItemMapper = cartLineItemMapper;
        this.cartMapper = cartMapper;
    }


    // Method to get cart by id
    public CartDTO findById(Integer id) {
        // Fetch the cart by id
        Cart cart = getCartById(id);

        // Get all cart items in the cart
        List<CartItem> cartLineItems = getListCartItemsByCartId(id);

        // Convert cart items to DTO
        List<CartItemResDTO> cartItemResDTO = cartLineItemMapper.listCartItemsToListCartItems(cartLineItems);

        // Convert cart to DTO and return
        return cartMapper.cartToCartResDTOInList(cart, cartItemResDTO);
    }

    // Method to get cart by id
    private Cart getCartById(Integer id){
        Optional<Cart> foundCart = cartRepository.findById(id);
        if (foundCart.isEmpty()) {
            throw new AppException(HttpStatus.NOT_FOUND.value(), "Cart not found");
        }
        return foundCart.get();
    }

    // Method to get all cart items in a cart
    private List<CartItem> getListCartItemsByCartId(Integer cartId){
        List<CartItem> listCartItems = cartItemRepository.findAllByCartId(cartId);
        if (listCartItems.isEmpty()){
            throw new AppException(HttpStatus.NO_CONTENT.value(), "Cart is empty");
        }
        return listCartItems;
    }
}