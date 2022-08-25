package com.hongrider.controller.study;

import com.hongrider.repository.study.HomeGreetingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/home")
public class HomeGreetingController {

    @Autowired
    private HomeGreetingRepository homeGreetingRepository;

    @GetMapping("/greeting")
    public String Greeting(){
        return homeGreetingRepository.greet();
    }
}
