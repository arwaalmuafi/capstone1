package com.example.capstone1.Model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor

public class MerchantStock {
    @NotEmpty(message = "id must be not empty")
    private String Id;

    @NotEmpty(message = "must not be empty")
    private String productId;

    @NotEmpty(message = "must not be empty")
    private String merchantId;

    @NotNull(message = "must not be empty")
    @Min(value = 0,message = "have to be more than 1 at start")
    private int stock;
}
