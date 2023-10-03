package lk.ijse.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lk.ijse.backend.entity.SubCategory;

@Repository
public interface SubCategoryRepository extends JpaRepository<SubCategory, Long> {
    @Query("SELECT s FROM SubCategory s JOIN s.categories c WHERE c.id = :category_id")
    List<SubCategory> findByCategoryId(@Param("category_id") Long id);
}
