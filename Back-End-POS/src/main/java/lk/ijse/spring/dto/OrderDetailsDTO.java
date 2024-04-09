package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailsDTO {
    private String oid;
    private String itemCode;
    private int qty;
    private double unitPrice;

}
