package com.rohit.SpringEcom.controller;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

//@Entity
@RestController
public class HelloController {

    @Id
    private  int id;
//    private String Name;

    @GetMapping("/hello")
    public  String great(){
        return "Welcome ... " ;
    }
}
