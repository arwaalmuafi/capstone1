package com.example.capstone1.Controller;

import com.example.capstone1.ApiReasponse.ApiReasponse;
import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.User;
import com.example.capstone1.Service.CategoryService;
import com.example.capstone1.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cap/v1/category")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;


    @GetMapping("/get")
    public ResponseEntity getCategory(){
        ArrayList<Category> categories=categoryService.getCategories();
        return ResponseEntity.status(200).body(categories);
    }

    @PostMapping("/add")
    public ResponseEntity addCategory(@RequestBody @Valid Category category , Errors errors){
        if(errors.hasErrors()){
            String massage=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }

        categoryService.addCategories(category);
        return ResponseEntity.status(200).body(new ApiReasponse("Category added"));
    }
    @PutMapping("/update/{id}")
    public ResponseEntity updateCategory (@PathVariable String id,@RequestBody @Valid Category category,Errors errors){
        if(errors.hasErrors()){
            String massage=errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }
        boolean isUpdate=categoryService.updateCategories(category,id);

        if(isUpdate){
            return ResponseEntity.status(200).body(new ApiReasponse("Category updated"));
        }
        return ResponseEntity.status(400).body(new ApiReasponse("Category not found"));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteCategory(@PathVariable String id){
        boolean isDelete=categoryService.deleteCategory(id);
        if(isDelete){
            return ResponseEntity.status(200).body(new ApiReasponse("Category is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiReasponse("Category is not found "));
    }

    //5
    @GetMapping("/active")
    public ResponseEntity getActiveCategories() {
        ArrayList<Category> activeCategories = categoryService.getActiveCategories();
        return ResponseEntity.status(200).body(activeCategories);
    }

    @PutMapping("/done/{categoryId}")
    public ResponseEntity markAsActive(@PathVariable String categoryId){
        boolean isActive=categoryService.markAsActive(categoryId);
        if (isActive){
            return ResponseEntity.status(200).body(new ApiReasponse("category is active"));
        }
        return ResponseEntity.status(400).body(new ApiReasponse("category is not found"));
    }



}
