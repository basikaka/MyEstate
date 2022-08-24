package com.hongrider.controller;

import com.hongrider.entity.User;
import com.hongrider.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/")
    public String Greeting(){
        return "Hello, World";
    }

    @GetMapping("/all/{page}/{size}")
    public Page<User> findAll(@PathVariable("page") Integer page,
                              @PathVariable("size") Integer size){
        Pageable pageable = PageRequest.of(page-1, size);
        return userRepository.findAll( pageable );
    }

    @PostMapping("/save")
    public String save(@RequestBody User user){
        User saved = userRepository.save( user );

        if(saved != null ){
            return "succeed";
        }else {
            return "failed";
        }
    }

    @GetMapping("/select/{id}")
    public User findById(@PathVariable("id") Integer id){
        return userRepository.findById( id ).get();
    }

    @PutMapping("/update")
    public String update(@RequestBody User user){
        User saved = userRepository.save( user );

        if(saved != null ){
            return "succeed";
        }else {
            return "failed";
        }
    }

    @DeleteMapping("/delete/{id}")
    public void deleteById(@PathVariable("id") Integer id){
        userRepository.deleteById(id);

    }

}
