package lk.ijse.gdse66.spring.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @Null(message = "Code is Auto Generated.")
    String code;
    @NotBlank(message = "Description Can Not Be Null.")
    @Pattern(regexp = "[A-Za-z ]+", message = "Description is Not Valid")
    String description;
    @NotBlank(message = "Quantity Can Not Be Null.")
    @Pattern(regexp = "[0-9]+", message = "Quantity is Not Valid")
    int qty;
    @NotBlank(message = "UnitPrice Can Not Be Null.")
    @Pattern(regexp = "\\d+(\\.\\d{1,2})?", message = "UnitPrice is Not Valid")
    double unitPrice;
}
