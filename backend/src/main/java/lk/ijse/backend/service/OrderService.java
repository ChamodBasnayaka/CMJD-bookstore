package lk.ijse.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.ijse.backend.entity.Order;

@Service
public interface OrderService {
    List<Order> getAllOrders();

    Order createOrder(Order order);

    Order getOrderById(Long id);

    Order updateOrder(Order order, Long id);

    void deleteOrder(Long id);
}
