package com.example.security.service;

import com.example.security.entity.Category;
import com.example.security.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    CategoryRepository categoryRepository;

    public List<Category> categoryList(){
        return categoryRepository.findAll();
    }

    public ResponseEntity<String > add(Category category){
        boolean b = categoryRepository.existsByName(category.getName());
        if (!b){
            categoryRepository.save(category);
            return new ResponseEntity<>("Categoriya qo'shildi", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Bunday categoriya mavjud", HttpStatus.FOUND);
    }
}
