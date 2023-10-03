package lk.ijse.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.ijse.backend.entity.Category;

@Service
public interface CategoryService {
    List<Category> getAllCategories();

    Category getCategoryById(Long id);

    Category createCategory(Category category);

    Category updateCategory(Category category, Long id);

    void deleteCategory(Long id);

    Category addSubcategoryToCategory(Long category_id, Long subcategory_id);

}
