package com.management.csit314_project.Controller;

import com.management.csit314_project.DTO.CartDTO;
import com.management.csit314_project.DTO.CartItemResDTO;
import com.management.csit314_project.Service.CartService;
import com.management.csit314_project.Service.CartItemService;
import com.management.csit314_project.System.Response.Result;
import com.management.csit314_project.System.Response.StatusCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart")
public class CartController {

    private final CartService cartService;
    private final CartItemService cartItemService;

    public CartController(CartService cartService, CartItemService cartItemService) {
        this.cartService = cartService;
        this.cartItemService = cartItemService;
    }

    // Get cart by id
    @GetMapping("/{id}")
    public Result getCartById(@PathVariable Integer id) {
        CartDTO cart = cartService.findById(id);
        return new Result(true, StatusCode.SUCCESS, "Cart retrieved successfully", cart);
    }

    // Create cart item
    @PostMapping("/item")
    public Result createCartItem(@RequestBody CartItemResDTO itemInCartDTO) {
        CartItemResDTO cartItem = cartItemService.createCartItem(itemInCartDTO);
        return new Result(true, StatusCode.SUCCESS, "Cart item created successfully", cartItem);
    }

//    // Delete cart item after order
//    @DeleteMapping("/item/{id}/order/{orderId}")
//    public Result deleteCartItemAfterOrder(@PathVariable Integer id, @PathVariable Integer orderId) {
//        cartItemService.deleteAfterOrder(id, orderId);
//        return new Result(true, StatusCode.SUCCESS, "Cart item deleted after order");
//    }
}