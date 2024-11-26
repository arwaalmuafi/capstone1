package com.example.capstone1.Model;

import jakarta.validation.constraints.AssertFalse;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Category {
    @NotEmpty(message = "id must be not empty")
    private String Id;

    @NotEmpty(message = "name must be not empty")
    @Size(min = 3,message = "have to be more than 3 length long")
    private String name;

    @AssertFalse
    private boolean isActive;

}
