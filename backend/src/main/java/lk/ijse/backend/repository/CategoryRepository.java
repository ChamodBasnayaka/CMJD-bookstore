package lk.ijse.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import lk.ijse.backend.entity.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
