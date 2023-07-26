package com.example.security.controller;

import com.example.security.dto.ProductDto;
import com.example.security.entity.Product;
import com.example.security.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @PreAuthorize(value = "hasAnyRole('USER', 'ADMIN','OWNER')")
    @GetMapping("")
    public List<Product> list(){
        return productService.list();
    }
    @PreAuthorize(value = "hasAnyRole('USER', 'ADMIN','OWNER')")
    @GetMapping("/{id}")
    public Product product(@PathVariable Integer id){
        return productService.listById(id);
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','OWNER')")
    @PostMapping
    public ResponseEntity<String> response(@RequestBody ProductDto product){
        return productService.response(product);
    }

    @PreAuthorize(value = "hasAnyRole('OWNER')")
    @PutMapping("/{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody ProductDto product){
        return productService.update(id,product);
    }

    @PreAuthorize(value = "hasAnyRole('OWNER')")
    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        return productService.delete(id);
    }

    @PreAuthorize(value = "hasAnyRole('USER', 'ADMIN','OWNER')")
    @GetMapping("/category/{id}")
    public List<Product> productList(@PathVariable Integer id){
        return productService.productListByCategoryId(id);
    }

    @GetMapping("/{name}")
    public List<Product> productList(@PathVariable String name){
        return productService.productListByName(name);
    }

}
