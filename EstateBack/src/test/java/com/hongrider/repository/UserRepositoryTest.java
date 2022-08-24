package com.hongrider.repository;

import com.hongrider.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void findAll(){
        System.out.println(userRepository.findAll());
    }

    @Test
    @Transactional
    void save(){
        User user = new User();
        user.setName("shiny");
        user.setAlias("璇妮");
        user.setRole("物业");
        user.setEstate("珠江新城");
        user.setEmail("shiny@qq.com");
        user.setPassword("111111");

        User savedUser = userRepository.save( user );
        System.out.println("测试保存：" + savedUser);
    }

    @Test
    void findById() {
        System.out.println( userRepository.findById( 9) );
    }

    @Test
    @Transactional
    void update(){
        User user = userRepository.findById( 5 ).get();
        user.setEstate( "南国花园");
        userRepository.save( user);
        System.out.println(user);
    }

    @Test
    @Transactional
    void delete(){
        userRepository.deleteById(4);
    }

    @Test
    void chinese(){
        System.out.println("好吃");
    }
}