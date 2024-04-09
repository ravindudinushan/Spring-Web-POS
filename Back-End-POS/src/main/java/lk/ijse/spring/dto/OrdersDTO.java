package lk.ijse.spring.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrdersDTO {
    private String oid;
    private String date;
    private String cusID;
    private List<OrderDetailsDTO> orderDetails;

}
