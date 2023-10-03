package lk.ijse.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import lk.ijse.backend.entity.BookDetails;

@Repository
public interface BooksRepository extends JpaRepository<BookDetails, Long> {
    @Query("SELECT bd FROM BookDetails bd WHERE bd.category.category_id = :category_id")
    List<BookDetails> findByCategoryId(@Param("category_id") Long id);

    @Query("SELECT bd FROM BookDetails bd WHERE bd.subCategory.subcategory_id = :subcategory_id")
    List<BookDetails> findBySubCategoryId(@Param("subcategory_id") Long id);

    @Query("SELECT i FROM BookDetails i WHERE i.category.category_id = :categoryId AND i.subCategory.subcategory_id = :subcategoryId")
    List<BookDetails> findByCategoryAndSubCategoryId(@Param("categoryId") Long category_id,
            @Param("subcategoryId") Long subcategory_id);

}
