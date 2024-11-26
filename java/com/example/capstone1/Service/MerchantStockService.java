package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
public class MerchantStockService {
    ArrayList<MerchantStock> merchantStocks=new ArrayList();

    public ArrayList<MerchantStock> getMerchant() {
        return merchantStocks;
    }

    public void addMerchantStock(MerchantStock merchantStock){
        merchantStocks.add(merchantStock);
    }

    public boolean updateMerchantStock(MerchantStock merchantStock,String id){
        for (int i = 0; i <merchantStocks.size() ; i++) {
            if(merchantStocks.get(i).getId().equals(id)){
              merchantStocks.set(i,merchantStock);
                return true;
            }

        }
        return false;
    }

    public boolean deleteMerchantStock(String id){
        for (int i = 0; i <merchantStocks.size() ; i++) {
            if(merchantStocks.get(i).getId().equals(id)){
                merchantStocks.remove(i);
                return true;
            }
        }
        return false;

    }

    //11
    public boolean addStock(String productId, String merchantId, int additionalStock) {
        for (MerchantStock stock : merchantStocks) {
            if (stock.getMerchantId().equals(merchantId) && stock.getProductId().equals(productId)) {
                stock.setStock(stock.getStock() + additionalStock);
                return true;
            }
        }
        return false;
    }

    //4
    public boolean isStockAvailable(String productId, String merchantId, int quantity) {
        for (MerchantStock stock : merchantStocks) {
            if (stock.getMerchantId().equals(merchantId) && stock.getProductId().equals(productId)) {
                return stock.getStock() >= quantity;
            }
        }
        return false;
    }






}
