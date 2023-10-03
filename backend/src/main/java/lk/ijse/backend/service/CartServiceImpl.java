package lk.ijse.backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.ijse.backend.entity.ShoppingCart;
import lk.ijse.backend.repository.CartRepository;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public ShoppingCart createShoppingCart(ShoppingCart cart) {
        return cartRepository.save(cart);
    }

}
