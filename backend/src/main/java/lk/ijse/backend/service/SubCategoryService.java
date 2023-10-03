package lk.ijse.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.ijse.backend.entity.SubCategory;

@Service
public interface SubCategoryService {
    List<SubCategory> getAllSubCategories();

    SubCategory getSubCategoryById(Long id);

    SubCategory createSubCategory(SubCategory subCategory);

    SubCategory updateSubCategory(SubCategory subCategory, Long id);

    void deleteSubcategory(Long id);

    List<SubCategory> getSubCategoriesByCategoryId(Long category_id);

}
