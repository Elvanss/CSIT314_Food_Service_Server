package com.management.csit314_project.Controller;

import com.management.csit314_project.DTO.CartItemResDTO;
import com.management.csit314_project.Service.CartItemService;
import com.management.csit314_project.System.Response.Result;
import com.management.csit314_project.System.Response.StatusCode;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/cart/item")
public class CartItemController {

    private final CartItemService cartItemService;

    public CartItemController(CartItemService cartItemService) {
        this.cartItemService = cartItemService;
    }

    // Create cart item
    @PostMapping("/create")
    public Result createCartItem(@RequestBody CartItemResDTO itemInCartDTO) {
        CartItemResDTO cartItem = cartItemService.createCartItem(itemInCartDTO);
        return new Result(true, StatusCode.SUCCESS, "Cart item created successfully", cartItem);
    }

    // Delete cart item after order
    @DeleteMapping("/{id}/order/{orderId}")
    public Result deleteCartItemAfterOrder(@PathVariable Integer id, @PathVariable Integer orderId) {
        cartItemService.deleteAfterOrder(id, orderId);
        return new Result(true, StatusCode.SUCCESS, "Cart item deleted after order");
    }
}