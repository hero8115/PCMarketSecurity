package com.example.security.service;

import com.example.security.dto.TypeDto;
import com.example.security.entity.Product;
import com.example.security.entity.Type;
import com.example.security.repository.ProductRepository;
import com.example.security.repository.TypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TypeService {

    @Autowired
    TypeRepository typeRepository;

    @Autowired
    ProductRepository productRepository;



    public List<Type> typeList(){
        return typeRepository.findAll();
    }

    public ResponseEntity<String> add(TypeDto typeDto){
        Optional<Product> byId = productRepository.findById(typeDto.getProduct_id());
        boolean b = typeRepository.existsByName(typeDto.getName());
        if (!b && byId.isPresent()){
            Type type = new Type();
            type.setName(type.getName());
            type.setProduct(byId.get());
            Type save = typeRepository.save(type);
            return new ResponseEntity<>("Malumot saqlandi", HttpStatus.OK);
        }
        return new ResponseEntity<>("ERROR", HttpStatus.NOT_FOUND);
    }

    public List<Type> typeListByProductId(Integer id){
        List<Type> all = typeRepository.findAll();
        List<Type> typeList = new ArrayList<>();
        for (Type t: all){
            if (t.getId()==id){
                typeList.add(t);
            }
        }
        return typeList;
    }
}
