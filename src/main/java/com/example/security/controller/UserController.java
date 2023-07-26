package com.example.security.controller;

import com.example.security.entity.User;
import com.example.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService userService;

    @PreAuthorize(value = "hasAnyRole('USER','ADMIN','OWNER')")
    @GetMapping
    public List<User> userList(){
        return userService.userList();
    }

    @PreAuthorize(value = "hasAnyRole('USER','ADMIN','OWNER')")
    @GetMapping("{id}")
    public User user(@PathVariable Integer id){
        return userService.listById(id);
    }

    @PreAuthorize(value = "hasAnyRole('USER','ADMIN','OWNER')")
    @PostMapping
    public ResponseEntity<String> add(@RequestBody User user){
        return userService.add(user);
    }

    @PreAuthorize(value = "hasAnyRole('USER','ADMIN','OWNER')")
    @PutMapping("{id}")
    public ResponseEntity<String> update(@PathVariable Integer id, @RequestBody User user){
        return userService.update(id, user);
    }

    @PreAuthorize(value = "hasAnyRole('USER','ADMIN','OWNER')")
    @DeleteMapping("{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        return userService.delete(id);
    }

}
