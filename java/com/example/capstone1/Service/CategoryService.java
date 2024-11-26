package com.example.capstone1.Service;

import com.example.capstone1.Model.Category;
import com.example.capstone1.Model.Product;
import com.example.capstone1.Model.User;
import lombok.Data;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;

@Service
public class CategoryService {
    private final ProductService productService;
    ArrayList<Category>  categories=new ArrayList();

    public CategoryService(ProductService productService) {
        this.productService = productService;
    }

    public ArrayList<Category> getCategories() {
        return categories;
    }

    public void addCategories(Category category){
        categories.add(category);
    }

    public boolean updateCategories(Category category ,String id){
        for (int i = 0; i <categories.size() ; i++) {
            if(categories.get(i).getId().equals(id)){
                categories.set(i,category);
                return true;
            }

        }
        return false;
    }

    public boolean deleteCategory(String id){
        for (int i = 0; i <categories.size() ; i++) {
            if(categories.get(i).getId().equals(id)){
                categories.remove(i);
                return true;
            }
        }
        return false;

    }

    //5
    public ArrayList<Category> getActiveCategories() {
        ArrayList<Category> activeCategories = new ArrayList<>();
        for (Category category : categories) {
            if (category.isActive()) {
                activeCategories.add(category);
            }
        }
        return activeCategories;
    }

    public boolean markAsActive(@PathVariable String categoryId){
        for(Category category:categories){
            if(category.getId().equals(categoryId)){
                category.setActive(true);
                return true;

            }
        }
        return false;
    }


}
