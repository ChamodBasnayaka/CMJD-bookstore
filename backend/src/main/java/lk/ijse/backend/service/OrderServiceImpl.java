package lk.ijse.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.ijse.backend.entity.Order;
import lk.ijse.backend.repository.OrderRepository;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll();
    }

    @Override
    public Order createOrder(Order order) {

        return orderRepository.save(order);
    }

    @Override
    public Order getOrderById(Long id) {
        return orderRepository.findById(id).orElse(null);
    }

    @Override
    public Order updateOrder(Order order, Long id) {
        Order selectedOrder = getOrderById(id);
        selectedOrder.setOrderedBooks(order.getOrderedBooks());
        selectedOrder.setShippingDetails(order.getShippingDetails());
        selectedOrder.setStatus(order.getStatus());
        selectedOrder.setUser(order.getUser());
        selectedOrder.setTotal(order.getTotal());
        return orderRepository.save(order);
    }

    @Override
    public void deleteOrder(Long id) {
        orderRepository.deleteById(id);
    }

}
