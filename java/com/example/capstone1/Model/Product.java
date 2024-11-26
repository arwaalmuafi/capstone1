package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class Product {
    @NotEmpty(message = "id must be not empty")
    private String id;

    @NotEmpty(message = "name must be not empty")
    @Size(min = 3,message = "have to be more than 3 length long")
    private String name;

    @NotNull(message = "must be not empty")
    @Positive(message = "must be positive number")
    private int price;

    @NotEmpty(message = "must be not empty")
    private String categoryId;

    @NotNull(message = "sale Count must be not null")
    private int salesCount;

    @AssertFalse
    private boolean featured;


 }
