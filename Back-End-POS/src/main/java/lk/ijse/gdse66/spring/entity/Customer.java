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
public class Customer {
    @Id
    @Null(message = "ID is Auto Generated.")
    private String id;
    @NotBlank(message = "Name Can Not Be Null.")
    @Pattern(regexp = "[A-Za-z ]+", message = "Name is Not Valid")
    private String name;
    @NotBlank(message = "Address Can Not Be Null.")
    private String address;
    private double salary;
}
