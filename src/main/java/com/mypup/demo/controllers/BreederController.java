package com.mypup.demo.controllers;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class BreederController {
    private BreederRepository breeders;
    private PasswordEncoder passwordEncoder;

    public BreederController(BreederRepository breeders, PasswordEncoder passwordEncoder) {
        this.breeders = (BreederRepository) breeders;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignUpForm(Model model){
        model.addAttribute("breeder", new Breeder());
        return "breederPosts/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveBreeder(@ModelAttribute Breeder breeder){
        String hash = passwordEncoder.encode(breeder.getPassword());
        breeder.setPassword(hash);
        breeders.save(breeder);
        return "redirect:/login";
    }
}
