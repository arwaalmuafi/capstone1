package com.example.capstone1.Service;

import com.example.capstone1.Model.Merchant;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MerchantService {
    ArrayList<Merchant> merchants = new ArrayList();

    public ArrayList<Merchant> getMerchant() {
        return merchants;
    }

    public void addMerchant(Merchant merchant) {
        merchants.add(merchant);
    }

    public boolean updateMerchant(Merchant merchant, String id) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId().equals(id)) {
                merchants.set(i,merchant);
                return true;
            }

        }
        return false;
    }

    public boolean deleteMerchant(String id) {
        for (int i = 0; i < merchants.size(); i++) {
            if (merchants.get(i).getId().equals(id)) {
                merchants.remove(i);
                return true;
            }
        }
        return false;

    }


    //3
    public Merchant getBestRatedMerchant() {
        if (merchants.isEmpty()) {
            return null; // No merchants available
        }

        Merchant bestMerchant = null;
        double highestRating = 0.0;

        for (Merchant merchant : merchants) {
            if (merchant.getRating() > highestRating) {
                highestRating = merchant.getRating();
                bestMerchant = merchant;
            }
        }

        return bestMerchant;
    }


    //3
    public boolean rateMerchant(String merchantId, int rating) {
        if (rating < 1 || rating > 5) {
            return false;
        }

        for (Merchant merchant : merchants) {
            if (merchant.getId().equals(merchantId)) {
                ArrayList<Integer> ratings = new ArrayList<>();
                merchant.getRating();
                if (ratings == null) {
                    ratings = new ArrayList<>();
                    merchant.setRating(rating);
                }
                ratings.add(rating);

                int total = 0;
                for (int r : ratings) {
                    total += r;
                }
                merchant.setRating(total / ratings.size());
                return true;
            }
        }
        return false;
    }




}
