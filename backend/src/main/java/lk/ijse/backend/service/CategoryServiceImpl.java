package lk.ijse.backend.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.ijse.backend.entity.Category;
import lk.ijse.backend.entity.SubCategory;
import lk.ijse.backend.repository.CategoryRepository;
import lk.ijse.backend.repository.SubCategoryRepository;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    public CategoryServiceImpl() {
    }

    CategoryServiceImpl(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<Category> getAllCategories() {
        return categoryRepository.findAll();
    }

    @Override
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id).orElse(null);
    }

    @Override
    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(Category category, Long id) {
        Category existingCategory = getCategoryById(id);
        existingCategory.setCategory_name(category.getCategory_name());
        existingCategory.setDescription(category.getDescription());
        return categoryRepository.save(existingCategory);
    }

    @Override
    public void deleteCategory(Long id) {
        categoryRepository.deleteById(id);
    }

    @Override
    public Category addSubcategoryToCategory(Long category_id, Long subcategory_id) {
        Category category = categoryRepository.findById(category_id)
                .orElseThrow(() -> new NoSuchElementException("No category found in ID: " + category_id));
        SubCategory subCategory = subCategoryRepository.findById(subcategory_id)
                .orElseThrow(() -> new NoSuchElementException("No Subcategory found in ID: " + subcategory_id));

        Set<SubCategory> subCategories = category.getAssignedsubcategories();
        subCategories.add(subCategory);
        category.setAssignedsubcategories(subCategories);
        return categoryRepository.save(category);
    }

}
