package com.example.capstone1.Model;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.ArrayList;

@Data
@AllArgsConstructor
public class User {

    @NotEmpty(message = "must not be empty")
    private String id;

    @NotEmpty(message = "must not be empty, have to be more than 5 length long")
    @Size(min = 5, message = "Should be longer then 5")
    private String username;

    @NotEmpty(message = "must not be empty")
    @Pattern(regexp = "^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{6,}$"
            , message = "have to be more than 6 length long, must have characters and digits")
    private String password;

    @NotEmpty(message = "must not be empty")
    @Email(message = "must be valid email")
    private String email;

    @NotEmpty(message = "must not be empty")
    @Pattern(regexp = "^(Admin|Customer)$")
    private String role;

    @NotNull(message = "(must not be empty")
    @Positive
    private double balance;


}
