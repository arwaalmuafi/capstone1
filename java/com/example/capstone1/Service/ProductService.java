package com.example.capstone1.Service;

import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
@Service
@RequiredArgsConstructor
public class ProductService {

    private final MerchantStockService merchantStockService;
    private final UserService userService;


    ArrayList<Product> products = new ArrayList<>();

    public ArrayList<Product> getProducts() {
        return products;
    }

    public void addProduct(Product product) {
        products.add(product);
    }

    public boolean updateProduct(Product product, String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.set(i, product);
                return true;
            }
        }
        return false;
    }

    public boolean deleteProduct(String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }


    public Product getProductById(String id) {
        for (Product product : products) {
            if (product.getId().equals(id)) {
                return product;
            }
        }
        return null;
    }


    public Product searchById(Product product, String id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId().equals(id)) {
                return product;
            }

        }

        return null;

    }

    //1
    public boolean markProductAsFeatured(String productId) {
        for (Product product : products) {
            if (product.getId().equals(productId)) {
                product.setFeatured(true);
                return true;
            }
        }
        return false;
    }

    //1
    public ArrayList<Product> getFeaturedProducts() {
        ArrayList<Product> featuredProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.isFeatured()) {
                featuredProducts.add(product);
            }
        }
        return featuredProducts;
    }


    //2
    public void increaseSalesCount(Product product) {
        product.setSalesCount(product.getSalesCount() + 1);

    }

    public Product getBestSellingProduct() {
        if (products.isEmpty()) {
            return null;
        }

        Product bestSellingProduct = products.get(0);

        for (Product product : products) {
            if (product.getSalesCount() > bestSellingProduct.getSalesCount()) {
                bestSellingProduct = product;
            }
        }

        return bestSellingProduct;
    }


    public double applyDiscount(String merchantID, String productID, double discountPercentage) {
        Product product = getProductById(productID);
        if (product == null) {
            return -1;
        }

        MerchantStock stock = merchantStockService.getStockByProductAndMerchant(merchantID, productID);
        if (stock == null) {
            return -1;
        }

        if (!stock.getMerchantId().equals(merchantID)) {
            return -1;
        }

        double originalPrice = product.getPrice();
        double discountedPrice = originalPrice - (originalPrice * discountPercentage / 100);


        product.setPrice(discountedPrice);
        return discountedPrice;
    }

}




