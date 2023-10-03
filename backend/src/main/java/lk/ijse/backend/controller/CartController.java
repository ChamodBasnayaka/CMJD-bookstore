package lk.ijse.backend.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.backend.entity.ShoppingCart;
import lk.ijse.backend.service.CartService;

@CrossOrigin(origins = "*")
@RestController
public class CartController {
    @Autowired
    private CartService cartService;

    CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/shoppingcart")
    public ResponseEntity<?> createCart(@RequestBody ShoppingCart shoppingCart) {
        return ResponseEntity.status(HttpStatus.CREATED).body(cartService.createShoppingCart(shoppingCart));
    }
}
