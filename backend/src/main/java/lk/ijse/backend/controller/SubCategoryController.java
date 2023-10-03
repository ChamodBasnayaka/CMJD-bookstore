package lk.ijse.backend.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.backend.entity.SubCategory;
import lk.ijse.backend.payloads.response.MassegeResponse;
import lk.ijse.backend.repository.CategoryRepository;
import lk.ijse.backend.service.SubCategoryService;

@RestController
@CrossOrigin(origins = "*")
public class SubCategoryController {
    @Autowired
    private SubCategoryService subCategoryService;

    @Autowired
    CategoryRepository categoryRepository;

    SubCategoryController(SubCategoryService subCategoryService) {
        this.subCategoryService = subCategoryService;
    }

    @GetMapping("/subcategories")
    public ResponseEntity<?> getAllSubCategories() {
        try {
            List<SubCategory> subCategories = subCategoryService.getAllSubCategories();
            return ResponseEntity.status(HttpStatus.OK).body(subCategories);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MassegeResponse("Internal Server Error"));
        }
    }

    @GetMapping("/subcategories/{id}")
    public ResponseEntity<?> getSubCategoryById(@PathVariable Long id) {
        try {
            SubCategory subCategory = subCategoryService.getSubCategoryById(id);
            return ResponseEntity.status(HttpStatus.OK).body(subCategory);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MassegeResponse("No Tipe in given ID: " + id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MassegeResponse("Internal Server Error"));
        }
    }

    @PutMapping("/subcategories/{id}")
    public ResponseEntity<?> updateSubCategory(@RequestBody SubCategory subCategory, @PathVariable Long id) {
        try {
            SubCategory existingSubCategory = subCategoryService.updateSubCategory(subCategory, id);
            return ResponseEntity.status(HttpStatus.OK).body(existingSubCategory);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MassegeResponse("Internal Server Error"));
        }
    }

    @DeleteMapping("/subcategories/{id}")
    public ResponseEntity<?> deleteSubCategory(@PathVariable Long id) {
        try {
            subCategoryService.deleteSubcategory(id);
            return ResponseEntity.status(HttpStatus.OK).body(new MassegeResponse("Successfully deleted"));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MassegeResponse("Internal Server Error"));
        }
    }

    @GetMapping("/categories/{category_id}/subcategories")
    public ResponseEntity<?> getSubcategoriesByCategoryId(@PathVariable Long category_id) {
        if (!categoryRepository.existsById(category_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new MassegeResponse("No Category Found"));
        } else {
            try {
                List<SubCategory> subCategories = subCategoryService.getSubCategoriesByCategoryId(category_id);
                return ResponseEntity.status(HttpStatus.OK).body(subCategories);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new MassegeResponse("Internal Server Error!"));
            }

        }
    }

    @PostMapping("/subcategories")
    public ResponseEntity<?> createSubCategory(@RequestBody SubCategory subCategory) {

        try {
            return ResponseEntity.status(HttpStatus.CREATED)
                    .body(subCategoryService.createSubCategory(subCategory));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MassegeResponse("Internal Server Error!"));
        }

    }

}
