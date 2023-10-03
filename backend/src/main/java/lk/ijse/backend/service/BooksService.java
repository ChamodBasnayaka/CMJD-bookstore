package lk.ijse.backend.service;

import java.util.List;

import org.springframework.stereotype.Service;

import lk.ijse.backend.dto.Amount;
import lk.ijse.backend.dto.BookDTO;
import lk.ijse.backend.entity.BookDetails;
import lk.ijse.backend.entity.Category;
import lk.ijse.backend.entity.SubCategory;

@Service
public interface BooksService {
    List<BookDetails> getAllBooks();

    BookDetails getBookById(Long id);

    BookDetails createBook(BookDTO bookDTO, Category category, SubCategory subCategory);

    BookDetails updateBook(BookDTO bookDTO, Long id, Category category, SubCategory subCategory);

    void deleteBook(Long id);

    List<BookDetails> getBooksByCategoryId(Long category_id);

    List<BookDetails> getBooksBySubCategoryId(Long subcategory_id);

    List<BookDetails> getBooksByCategoryAndSubCaregoryId(Long category_id, Long subcategory_id);

    BookDetails updateAmount(Amount amount, Long id);

}
