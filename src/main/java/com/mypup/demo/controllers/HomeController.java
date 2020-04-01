package com.mypup.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Positive;

@Controller
public class HomeController {
    @GetMapping("/")
    @ResponseBody
    public String landingPage() {
        return "Welcome to Mypup!";
    }

    @GetMapping("/index")
    public String welcome() {
        return "index";
    }

    @GetMapping("/home")
    public String homePage(){
        return "breeder-posts/home";
    }



}