package lk.ijse.backend.service;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lk.ijse.backend.entity.SubCategory;
import lk.ijse.backend.repository.SubCategoryRepository;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    SubCategoryServiceImpl(SubCategoryRepository subCategoryRepository) {
        this.subCategoryRepository = subCategoryRepository;
    }

    @Override
    public List<SubCategory> getAllSubCategories() {
        return subCategoryRepository.findAll();
    }

    @Override
    public SubCategory getSubCategoryById(Long id) {
        return subCategoryRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("No SubCategory found in ID: " + id));
    }

    @Override
    public SubCategory createSubCategory(SubCategory subCategory) {
        return subCategoryRepository.save(subCategory);
    }

    @Override
    public SubCategory updateSubCategory(SubCategory subCategory, Long id) {
        SubCategory existingSubCategory = getSubCategoryById(id);
        existingSubCategory.setSubcategory_name(subCategory.getSubcategory_name());
        existingSubCategory.setDescription(subCategory.getDescription());
        return subCategoryRepository.save(existingSubCategory);
    }

    @Override
    public void deleteSubcategory(Long id) {
        subCategoryRepository.deleteById(id);
    }

    @Override
    public List<SubCategory> getSubCategoriesByCategoryId(Long category_id) {
        return subCategoryRepository.findByCategoryId(category_id);
    }

}
