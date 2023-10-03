package lk.ijse.backend.service;

import org.springframework.stereotype.Service;

import lk.ijse.backend.entity.ShoppingCart;

@Service
public interface CartService {
    ShoppingCart createShoppingCart(ShoppingCart cart);
}
