package com.example.capstone1.Controller;

import com.example.capstone1.ApiReasponse.ApiReasponse;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import com.example.capstone1.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/capston/v1/user")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;


    @GetMapping("/get")
    public ResponseEntity getUSer(){
       ArrayList<User> user=userService.getUsers();
       return ResponseEntity.status(200).body(user);
    }

    @PostMapping("/add")
    public ResponseEntity addUSer(@RequestBody @Valid User user , Errors errors){
                if(errors.hasErrors()){
                    String massage=errors.getFieldError().getDefaultMessage();
                    return ResponseEntity.status(400).body(massage);
                }

                userService.addUser(user);
                return ResponseEntity.status(200).body(new ApiReasponse("user added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateUser (@PathVariable String id,@RequestBody @Valid User user,Errors errors){
        if(errors.hasErrors()){
            String massage=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }
        boolean isUpdate=userService. updateUser(user,id);

        if(isUpdate){
            return ResponseEntity.status(200).body(new ApiReasponse("user updated"));
        }
        return ResponseEntity.status(400).body(new ApiReasponse("user not found"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable String id){
        boolean isDelete=userService.deleteUser(id);
        if(isDelete){
            return ResponseEntity.status(200).body(new ApiReasponse("user is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiReasponse("user is not found "));
    }


    @PostMapping("/buyProduct/{userId}/{productId}/{merchantId}")
    public ResponseEntity<String> buyProduct(@PathVariable String userId,@PathVariable String productId,@PathVariable String merchantId) {
        boolean isSuccess = userService.buyProduct(userId, productId, merchantId);

        if (isSuccess) {
            return ResponseEntity.status(200).body("Product purchased successfully.");
        }
        return ResponseEntity.status(400).body("Purchase failed.");
    }







}