package com.example.capstone1.Controller;

import com.example.capstone1.ApiReasponse.ApiReasponse;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Service.MerchantStockService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cap/v1/merchant/stock")
@RequiredArgsConstructor
public class MerchantStockController {

    private final MerchantStockService merchantstockService;

    @GetMapping("/get")
    public ResponseEntity getMerchantStock() {
        ArrayList<MerchantStock> merchantStocks = merchantstockService.getMerchant();
        return ResponseEntity.status(200).body(merchantStocks);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchantStock(@RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }

        merchantstockService.addMerchantStock(merchantStock);
        return ResponseEntity.status(200).body(new ApiReasponse("Merchant added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchantStock(@PathVariable String id, @RequestBody @Valid MerchantStock merchantStock, Errors errors) {
        if (errors.hasErrors()) {
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }
        boolean isUpdate = merchantstockService.updateMerchantStock(merchantStock, id);

        if (isUpdate) {
            return ResponseEntity.status(200).body(new ApiReasponse("Merchant updated"));
        }
        return ResponseEntity.status(400).body(new ApiReasponse("Merchant not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id) {
        boolean isDelete = merchantstockService.deleteMerchantStock(id);
        if (isDelete) {
            return ResponseEntity.status(200).body(new ApiReasponse("Merchant is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiReasponse("Merchant is not found "));
    }


    @PostMapping("/addStock/{productId}/{merchantId}/{additionalStock}")
    public ResponseEntity<String> addStock(@PathVariable String productId,
                                           @PathVariable String merchantId,
                                           @PathVariable int additionalStock) {
        boolean isSuccess = merchantstockService.addStock(productId, merchantId, additionalStock);

        if (isSuccess) {
            return ResponseEntity.status(200).body("Stock updated.");
        }
        return ResponseEntity.status(400).body(" product not found.");
    }

    //4
    @GetMapping("/checkStock/{productId}/{merchantId}/{quantity}")
    public ResponseEntity<?> checkStockAvailability(@PathVariable String productId,
                                                    @PathVariable String merchantId,
                                                    @PathVariable int quantity) {
        boolean isAvailable = merchantstockService.isStockAvailable(productId, merchantId, quantity);

        if (isAvailable) {
            return ResponseEntity.status(200).body(new ApiReasponse(" stock available."));
        }
        return ResponseEntity.status(400).body(new ApiReasponse(" stock not found."));
    }

}
