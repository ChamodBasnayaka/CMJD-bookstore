package lk.ijse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.ijse.backend.entity.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
