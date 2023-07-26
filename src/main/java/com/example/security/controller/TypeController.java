package com.example.security.controller;

import com.example.security.dto.TypeDto;
import com.example.security.entity.Type;
import com.example.security.service.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/type")
public class TypeController {

    @Autowired
    TypeService typeService;

    @PreAuthorize(value = "hasAnyRole('USER', 'ADMIN','OWNER')")
    @GetMapping
    public List<Type> typeList(){
        return typeService.typeList();
    }

    @PreAuthorize(value = "hasAnyRole('ADMIN','OWNER')")
    @PostMapping
    public ResponseEntity<String> add(@RequestBody TypeDto typeDto){
        return typeService.add(typeDto);
    }

    @PreAuthorize(value = "hasAnyRole('USER', 'ADMIN','OWNER')")
    @GetMapping("/{id}")
    public List<Type> typeListById(@PathVariable Integer id){
        return typeService.typeListByProductId(id);
    }
}
