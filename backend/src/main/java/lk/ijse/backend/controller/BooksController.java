package lk.ijse.backend.controller;

import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lk.ijse.backend.dto.Amount;
import lk.ijse.backend.dto.BookDTO;
import lk.ijse.backend.entity.BookDetails;
import lk.ijse.backend.entity.Category;
import lk.ijse.backend.entity.SubCategory;
import lk.ijse.backend.payloads.response.MassegeResponse;
import lk.ijse.backend.repository.CategoryRepository;
import lk.ijse.backend.repository.SubCategoryRepository;
import lk.ijse.backend.service.BooksService;

@CrossOrigin(origins = "*")
@RestController
public class BooksController {
    @Autowired
    private BooksService booksService;

    @Autowired
    CategoryRepository categoryRepository;

    @Autowired
    SubCategoryRepository subCategoryRepository;

    BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/books")
    public ResponseEntity<?> getAllBooks() {
        try {
            List<BookDetails> books = booksService.getAllBooks();
            return ResponseEntity.status(HttpStatus.OK).body(books);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/books/{id}")
    public ResponseEntity<?> getBookById(@PathVariable Long id) {
        try {
            BookDetails book = booksService.getBookById(id);
            return ResponseEntity.status(HttpStatus.OK).body(book);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/books/{id}")
    public ResponseEntity<?> updateBook(@ModelAttribute BookDTO bookDTO, @PathVariable Long id) {
        Category category = categoryRepository.findById(bookDTO.getCategory()).orElse(null);
        SubCategory subCategory = subCategoryRepository.findById(bookDTO.getSubcategory()).orElse(null);
        if (category != null) {
            try {
                BookDetails book = booksService.updateBook(bookDTO, id, category, subCategory);
                return ResponseEntity.status(HttpStatus.OK).body(book);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new MassegeResponse("Internal Server Error"));
            }
        } else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MassegeResponse("No Books found in Category ID: " + category));

        }

    }

    @DeleteMapping("/books/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        try {
            booksService.deleteBook(id);
            return ResponseEntity.status(HttpStatus.OK).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/categories/{category_id}/books")
    public ResponseEntity<?> getAllBooksByCategotyId(@PathVariable Long category_id) {
        if (!categoryRepository.existsById(category_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MassegeResponse("Category Not found in ID: " + category_id));
        } else {
            try {
                List<BookDetails> books = booksService.getBooksByCategoryId(category_id);
                return ResponseEntity.status(HttpStatus.OK).body(books);
            } catch (NoSuchElementException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new MassegeResponse("No Books found in Category: " + category_id));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new MassegeResponse("Internal Server Error"));
            }
        }
    }

    @GetMapping("/subcategories/{subcategory_id}/books")
    public ResponseEntity<?> getAllBooksBySubCategoryId(@PathVariable Long subcategory_id) {
        if (!subCategoryRepository.existsById(subcategory_id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No book type found in ID: " + subcategory_id);
        } else {
            try {
                List<BookDetails> books = booksService.getBooksBySubCategoryId(subcategory_id);
                return ResponseEntity.status(HttpStatus.OK).body(books);
            } catch (NoSuchElementException e) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(new MassegeResponse("No Books found in Category: " + subcategory_id));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new MassegeResponse("Internal Server Error"));
            }
        }
    }

    @PostMapping("/books")
    public ResponseEntity<?> createBook(@ModelAttribute BookDTO bookDTO) {

        Category category = categoryRepository.findById(bookDTO.getCategory()).orElse(null);
        SubCategory subCategory = subCategoryRepository.findById(bookDTO.getSubcategory()).orElse(null);
        if (subCategory != null && category != null) {
            try {
                BookDetails book = booksService.createBook(bookDTO, category, subCategory);
                return ResponseEntity.status(HttpStatus.CREATED).body(book);
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
            }
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new MassegeResponse("No Category or Sub category Found in ID: "));
        }

    }

    @GetMapping("/categories/{category_id}/subcategories/{subcategory_id}/books")
    public ResponseEntity<?> getBooksByCategoryAndSubCaregoryId(@PathVariable Long category_id,
            @PathVariable Long subcategory_id) {
        if (categoryRepository.existsById(category_id) && subCategoryRepository.existsById(subcategory_id)) {
            try {
                return ResponseEntity.status(HttpStatus.OK)
                        .body(booksService.getBooksByCategoryAndSubCaregoryId(category_id, subcategory_id));
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(new MassegeResponse("Internal server Error"));
            }
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new MassegeResponse("No books from given Category or Subcategory"));

    }

    @PostMapping("/books/{id}")
    public ResponseEntity<?> updateAmount(@RequestBody Amount amount, @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(booksService.updateAmount(amount, id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(new MassegeResponse("Internal server Error"));
        }
    }

}
