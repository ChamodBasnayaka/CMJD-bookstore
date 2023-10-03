package lk.ijse.backend.service;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import lk.ijse.backend.dto.Amount;
import lk.ijse.backend.dto.BookDTO;
import lk.ijse.backend.entity.BookDetails;
import lk.ijse.backend.entity.Category;
import lk.ijse.backend.entity.SubCategory;
import lk.ijse.backend.repository.BooksRepository;

@Service
public class BooksServiceImpl implements BooksService {
    @Autowired
    private BooksRepository booksRepository;
    @Value("${coverImages.derectory}")
    private String coverImages;

    BooksServiceImpl(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    @Override
    public List<BookDetails> getAllBooks() {
        return booksRepository.findAll();
    }

    @Override
    public BookDetails getBookById(Long id) {
        return booksRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Cannot find book by ID: " + id));
    }

    @Override
    public BookDetails createBook(BookDTO bookDTO, Category category, SubCategory subCategory) {
        BookDetails book = new BookDetails();
        book.setTitle(bookDTO.getTitle());
        book.setAuther(bookDTO.getAuther());
        book.setPrice(bookDTO.getPrice());
        book.setDescription(bookDTO.getDescription());
        book.setCategory(category);
        book.setSubCategory(subCategory);
        book.setQuantity(bookDTO.getQuantity());
        MultipartFile file = bookDTO.getProfileImage();
        String filename = file.getOriginalFilename();
        String path = coverImages + File.separator + filename;

        try {
            file.transferTo(new File(path));
        } catch (IllegalStateException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        book.setCoverImage(filename);
        return booksRepository.save(book);
    }

    @Override
    public BookDetails updateBook(BookDTO bookDTO, Long id, Category category, SubCategory subCategory) {
        BookDetails existingBook = getBookById(id);
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setAuther(bookDTO.getAuther());
        existingBook.setPrice(bookDTO.getPrice());
        existingBook.setDescription(bookDTO.getDescription());
        existingBook.setCategory(category);
        existingBook.setSubCategory(subCategory);
        existingBook.setQuantity(bookDTO.getQuantity());
        MultipartFile file = bookDTO.getProfileImage();
        String filename = file.getOriginalFilename();
        String path = coverImages + File.separator + filename;

        try {
            file.transferTo(new File(path));
        } catch (IllegalStateException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
        existingBook.setCoverImage(filename);
        return booksRepository.save(existingBook);
    }

    @Override
    public void deleteBook(Long id) {
        booksRepository.deleteById(id);
    }

    @Override
    public List<BookDetails> getBooksByCategoryId(Long category_id) {
        return booksRepository.findByCategoryId(category_id);
    }

    @Override
    public List<BookDetails> getBooksBySubCategoryId(Long subcategory_id) {
        return booksRepository.findBySubCategoryId(subcategory_id);
    }

    @Override
    public List<BookDetails> getBooksByCategoryAndSubCaregoryId(Long category_id, Long subcategory_id) {
        return booksRepository.findByCategoryAndSubCategoryId(category_id, subcategory_id);
    }

    @Override
    public BookDetails updateAmount(Amount amount, Long id) {
        BookDetails book = getBookById(id);
        book.setQuantity(book.getQuantity() - amount.getAmount());
        return booksRepository.save(book);

    }

}
