package com.quiz.controllers;


import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("/")
public class DefaultController {

    @GetMapping
    public String defaultMethod(){
        return "welcome to groot";
    }


}
