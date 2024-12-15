package com.example.jpa121.DTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AddressOUTDTO {


    private String area;

    private String street;

    private Integer buildingNumber;
}
