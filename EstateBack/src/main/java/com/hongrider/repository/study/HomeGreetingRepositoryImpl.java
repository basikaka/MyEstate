package com.hongrider.repository.study;

import org.springframework.stereotype.Service;

@Service
public class HomeGreetingRepositoryImpl implements HomeGreetingRepository{
    @Override
    public String greet() {
        return "Hello, World";
    }
}
