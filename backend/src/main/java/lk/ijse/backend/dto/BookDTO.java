package lk.ijse.backend.dto;

import org.springframework.web.multipart.MultipartFile;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class BookDTO {
    private String title;
    private String auther;
    private double price;
    private String description;
    private MultipartFile profileImage;
    private Long category;
    private Long subcategory;
    private int quantity;

}
