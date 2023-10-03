package lk.ijse.backend.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import jakarta.persistence.Table;

import lombok.Data;

@Entity
@Table(name = "soppingCarts")
@Data
public class ShoppingCart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long cart_id;
    @Column
    private Long order_id;
    @Column
    private String name;
    @Column
    private double price;
    @Column
    private String language;
    @Column
    private String category;
    @Column
    private int amount;
    @Column
    private double subTotal;

}
