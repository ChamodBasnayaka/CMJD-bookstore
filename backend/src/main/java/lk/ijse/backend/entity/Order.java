package lk.ijse.backend.entity;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import lk.ijse.backend.dto.ShippingDetails;
import lombok.Data;

@Entity
@Data
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long order_id;

    @Column(nullable = false)
    private String status;

    @Column(updatable = false)
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    @Column(nullable = false)
    private double total;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_book", joinColumns = { @JoinColumn(name = "order_id") }, inverseJoinColumns = {
            @JoinColumn(name = "book_id") })
    private Set<BookDetails> orderedBooks = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "user_id")
    private User user;

    @Embedded
    private ShippingDetails shippingDetails;

    @PrePersist
    protected void onCreate() {
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.updateAt = LocalDateTime.now();
    }

    public Order() {
        this.status = "Pending";

    }
}
