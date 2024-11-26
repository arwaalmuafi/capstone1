package com.example.capstone1.Controller;

import com.example.capstone1.ApiReasponse.ApiReasponse;
import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.User;
import com.example.capstone1.Service.MerchantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping("/cap/v1/merchant")
@RequiredArgsConstructor
public class MerchantController {

    private final MerchantService merchantService;

    @GetMapping("/get")
    public ResponseEntity getMerchant() {
        ArrayList<Merchant> merchant = merchantService.getMerchant();
        return ResponseEntity.status(200).body(merchant);
    }

    @PostMapping("/add")
    public ResponseEntity addMerchant(@RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }

        merchantService.addMerchant(merchant);
        return ResponseEntity.status(200).body(new ApiReasponse("Merchant added"));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity updateMerchant(@PathVariable String id, @RequestBody @Valid Merchant merchant, Errors errors) {
        if (errors.hasErrors()) {
            String massage = errors.getFieldError().getDefaultMessage();
            return ResponseEntity.status(400).body(massage);
        }
        boolean isUpdate = merchantService.updateMerchant(merchant, id);

        if (isUpdate) {
            return ResponseEntity.status(200).body(new ApiReasponse("Merchant updated"));
        }
        return ResponseEntity.status(400).body(new ApiReasponse("Merchant not found"));
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteMerchant(@PathVariable String id) {
        boolean isDelete = merchantService.deleteMerchant(id);
        if (isDelete) {
            return ResponseEntity.status(200).body(new ApiReasponse("Merchant is deleted"));
        }
        return ResponseEntity.status(400).body(new ApiReasponse("Merchant is not found "));
    }


    //3
    @PostMapping("/rate/{merchantId}/{rating}")
    public ResponseEntity rateMerchant(@PathVariable String merchantId, @PathVariable int rating) {
        boolean isRated = merchantService.rateMerchant(merchantId, rating);
        if (isRated) {
            return ResponseEntity.status(200).body(new ApiReasponse("Merchant rated."));
        }
        return ResponseEntity.status(400).body(new ApiReasponse("not found"));
    }



    //3
    @GetMapping("/bestRated")
    public ResponseEntity<?> getBestRatedMerchant() {
        Merchant bestMerchant = merchantService.getBestRatedMerchant();
        if (bestMerchant == null) {
            return ResponseEntity.status(400).body(new ApiReasponse("No merchants found."));
        }
        return ResponseEntity.status(200).body(bestMerchant);
    }




}

