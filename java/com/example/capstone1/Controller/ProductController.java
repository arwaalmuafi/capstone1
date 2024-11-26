package com.example.capstone1.Controller;

import com.example.capstone1.ApiReasponse.ApiReasponse;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cap/v1/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping("/get")
    public ResponseEntity getProducts() {
        ArrayList<Product> products = productService.getProducts();
        return ResponseEntity.status(200).body(products);
    }

    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }

        productService.addProduct(product);
        return ResponseEntity.status(200).body(new ApiReasponse("Product added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable String id, @RequestBody @Valid Product product, Errors errors) {
        if (errors.hasErrors()) {
            String message = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(message);
        }
        boolean isUpdated = productService.updateProduct(product, id);

        if (isUpdated) {
            return ResponseEntity.status(200).body(new ApiReasponse("Product updated"));
        }
        return ResponseEntity.status(400).body(new ApiReasponse("Product not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable String id) {
        boolean isDeleted = productService.deleteProduct(id);
        if (isDeleted) {
            return ResponseEntity.status(200).body(new ApiReasponse("Product deleted"));
        }
        return ResponseEntity.status(400).body(new ApiReasponse("Product not found"));
    }


    //1
    @PutMapping("/feature/{productId}")
    public ResponseEntity markAsFeatured(@PathVariable String productId) {
        boolean success = productService.markProductAsFeatured(productId);
        if (success) {
            return ResponseEntity.status(200).body(new ApiReasponse("Product marked as featured."));
        } else {
            return ResponseEntity.status(400).body("Product not found.");
        }
    }
    //1
    @GetMapping("/get/featured")
    public ResponseEntity<ArrayList<Product>> getFeaturedProducts() {
        ArrayList<Product> featuredProducts = productService.getFeaturedProducts();
        return ResponseEntity.status(200).body(featuredProducts);
    }


    //2
    @GetMapping("/best-selling")
    public ResponseEntity getBestSellingProduct() {
        Product bestSellingProduct = productService.getBestSellingProduct();

        if (bestSellingProduct != null) {
            return ResponseEntity.status(200).body(bestSellingProduct);
        } else {
            return ResponseEntity.status(404).body(new ApiReasponse("No products found."));
        }
    }




}
