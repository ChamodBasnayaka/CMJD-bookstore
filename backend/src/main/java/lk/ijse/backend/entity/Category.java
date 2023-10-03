package lk.ijse.backend.entity;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long category_id;

    @Column(nullable = false, unique = true)
    private String category_name;

    @Column(nullable = true)
    private String description;

    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "categories_subcategories", joinColumns = {
            @JoinColumn(name = "category_id") }, inverseJoinColumns = { @JoinColumn(name = "subcategory_id") })
    private Set<SubCategory> assignedsubcategories = new HashSet<>();

}
