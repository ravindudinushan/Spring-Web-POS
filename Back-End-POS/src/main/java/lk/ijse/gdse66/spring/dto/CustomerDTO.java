package lk.ijse.gdse66.spring.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomerDTO {
    @Null(message = "ID is auto generate")
    private String id;
    @NotBlank(message = "Name can not be null")
    @Pattern(regexp = "[A-Za-z]+", message = "Name is not valid")
    private String name;
    @NotBlank(message = "Address can not be null")
    private String address;
    @NotBlank(message = "Salary can not be null")
    private double salary;
}
