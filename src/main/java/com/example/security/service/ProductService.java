package com.example.security.service;

import com.example.security.dto.ProductDto;
import com.example.security.entity.Category;
import com.example.security.entity.Product;
import com.example.security.repository.CategoryRepository;
import com.example.security.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Product> list(){
        return productRepository.findAll();
    }
    public Product listById(Integer id){
        return productRepository.findById(id).get();
    }

    public ResponseEntity<String> response(ProductDto productDto){
        Optional<Category> byId = categoryRepository.findById(productDto.getCategory_id());
        if (byId.isPresent()) {
            Product product = new Product();
            product.setName(productDto.getName());
            product.setModel(productDto.getModel());
            product.setPrice(productDto.getPrice());
            product.setDescription(productDto.getDescription());
            product.setCategory(byId.get());
            Product save = productRepository.save(product);
            return new ResponseEntity<>("Mahsulot saqlandi", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Bunday Id lik categoriya mavjud emas", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> update(Integer id, ProductDto productDto){
        Optional<Product> byId = productRepository.findById(id);
        Optional<Category> byId1 = categoryRepository.findById(productDto.getCategory_id());
        if (byId.isPresent()){
            Product product1 = byId.get();
            product1.setName(productDto.getName());
            product1.setDescription(productDto.getDescription());
            product1.setPrice(productDto.getPrice());
            product1.setModel(productDto.getModel());
            product1.setCategory(byId1.get());
            Product save = productRepository.save(product1);
            return new ResponseEntity<>("Malumotlar o'zgartirildi",HttpStatus.OK);
        }
        return new ResponseEntity<>("Bunday Id lik mahsulot topilmadi", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> delete(Integer id){
        Optional<Product> byId = productRepository.findById(id);
        if (byId.isPresent()){
            productRepository.deleteById(id);
            return new ResponseEntity<>("Mahsulot o'chirildi", HttpStatus.OK);
        }
        return new ResponseEntity<>("Bunday Id lik mahsulot topilmadi", HttpStatus.NOT_FOUND);
    }

    public List<Product> productListByCategoryId(Integer id){
        List<Product> all = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        for (Product p:all){
            if (p.getCategory().getId()==id){
                productList.add(p);
            }
        }
        return productList;
    }
    public List<Product> productListByName(String name){
        List<Product> all = productRepository.findAll();
        List<Product> productList = new ArrayList<>();
        for (Product p: all){
            if (p.getName().equals(name)){
                productList.add(p);
            }
        }
        return productList;
    }
}
