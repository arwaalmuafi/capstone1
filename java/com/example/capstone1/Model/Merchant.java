package com.example.capstone1.Model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class Merchant {
    @NotEmpty(message = "id must be not empty")
    private String Id;

    @NotEmpty(message = "name must be not empty")
    @Size(min = 3, message = "have to be more than 3 length long")
    private String name;

    @NotNull(message = "connote be null")
    private int rating;

}
