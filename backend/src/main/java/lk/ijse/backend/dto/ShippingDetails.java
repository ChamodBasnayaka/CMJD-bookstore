package lk.ijse.backend.dto;

import jakarta.persistence.Embeddable;
import lombok.Data;

@Embeddable
@Data
public class ShippingDetails {
    private String address;
    private String postalCode;
    private String mobile;

}
