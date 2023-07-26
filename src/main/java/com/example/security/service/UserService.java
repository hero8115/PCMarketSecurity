package com.example.security.service;

import com.example.security.entity.Product;
import com.example.security.entity.User;
import com.example.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<String> add(User user){
        boolean b = userRepository.existsByUsername(user.getUsername());
        if (!b) {
            userRepository.save(user);
            return new ResponseEntity<>("User qo'shildi", HttpStatus.ACCEPTED);
        }
        return new ResponseEntity<>("Bunday username ro'yxatga olingan",HttpStatus.LOCKED);
    }

    public User listById(Integer id){
        return userRepository.findById(id).get();
    }

    public List<User> userList(){
        return userRepository.findAll();
    }

    public ResponseEntity<String> update(Integer id, User user){
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()){
            User user1 = byId.get();
            user1.setUsername(user.getUsername());
            user1.setPassword(user.getPassword());
            String s = user.getRole().toUpperCase();
            user1.setRole(s);
            User save = userRepository.save(user1);
            return new ResponseEntity<>("Malumotlar o'zgartirildi",HttpStatus.OK);
        }
        return new ResponseEntity<>("Bunday Id lik user topilmadi", HttpStatus.NOT_FOUND);
    }

    public ResponseEntity<String> delete(Integer id){
        Optional<User> byId = userRepository.findById(id);
        if (byId.isPresent()){
            userRepository.deleteById(id);
            return new ResponseEntity<>("User o'chirildi", HttpStatus.OK);
        }
        return new ResponseEntity<>("Bunday Id lik user topilmadi", HttpStatus.NOT_FOUND);
    }


}
