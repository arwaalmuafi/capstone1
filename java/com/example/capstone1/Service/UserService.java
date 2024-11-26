package com.example.capstone1.Service;

import com.example.capstone1.Model.MerchantStock;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {

    private final ProductService productService;
    private final MerchantStockService merchantStockService;
     ArrayList<Product> cart = new ArrayList<>();


    ArrayList<User> users = new ArrayList();

    public ArrayList<User> getUsers() {
        return users;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public boolean updateUser(User user, String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.set(i, user);
                return true;
            }

        }
        return false;
    }

    public boolean deleteUser(String id) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getId().equals(id)) {
                users.remove(i);
                return true;
            }
        }
        return false;

    }


    //12
        public boolean buyProduct(String userId, String productId, String merchantId) {
            User user = null;
            MerchantStock stock = null;

            for (User u : users) {
                if (u.getId().equals(userId)) {
                    user = u;
                    break;
                }
            }
            if (user == null) {
                return false;
            }
            for (MerchantStock ms : merchantStockService.merchantStocks) {
                if (ms.getProductId().equals(productId) && ms.getMerchantId().equals(merchantId)) {
                    stock = ms;
                    break;
                }
            }
            if (stock == null || stock.getStock() <= 0) {
                return false;
            }

            Product product = productService.getProductById(productId);
            if (product == null) {
                return false;
            }

            if (user.getBalance() < product.getPrice()) {
                return false;
            }

            user.setBalance(user.getBalance() - product.getPrice());
            stock.setStock(stock.getStock() - 1);
            productService.increaseSalesCount(product);


            return true;
        }


        //11
    public User getUserById(String id) {
        for (User user : users) {
            if (user.getId().equals(id)) {
                return user;
            }
        }
        return null;
    }

//2
    public boolean transferBalance(String fromUserId, String toUserId, double amount) {
        if (amount <= 0) {
            return false;
        }

        User sender = null;
        User receiver = null;

        for (User user : users) {
            if (user.getId().equals(fromUserId)) {
                sender = user;
            }
            if (user.getId().equals(toUserId)) {
                receiver = user;
            }
        }

        if (sender == null || receiver == null) {
            return false;
        }

        if (sender.getBalance() < amount) {
            return false;
        }


        sender.setBalance(sender.getBalance() - amount);
        receiver.setBalance(receiver.getBalance() + amount);

        return true;
    }

    //1
    public boolean addToWishlist(String userId, String productId) {
        User user = getUserById(userId);
        if (user == null) {
            return false;
        }


        Product product = productService.getProductById(productId);
        if (product == null) {
            return false;
        }


        if (!user.getWishlist().contains(productId)) {
            user.getWishlist().add(productId);
            return true;
        }

        return false;
    }

    //1
    public ArrayList<Product> getWishlist(String userId) {
        User user = getUserById(userId);
        if (user == null) {
            return new ArrayList<>();
        }

        ArrayList<Product> wishlistProducts = new ArrayList<>();
        for (String productId : user.getWishlist()) {
            Product product = productService.getProductById(productId);
            if (product != null) {
                wishlistProducts.add(product);
            }
        }

        return wishlistProducts;
    }




}
























