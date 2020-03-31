package com.mypup.demo.controllers;

import com.mypup.demo.models.User;
import com.mypup.demo.repos.UserRepo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UsersController {
    private UserRepo usersdao;
    private PasswordEncoder passwordEncoder;

    public UsersController(UserRepo users, PasswordEncoder passwordEncoder) {
        this.usersdao = users;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/sign-up")
    public String showSignUpForm(Model model){
        model.addAttribute("user", new User());
        return "users/sign-up";
    }

    @PostMapping("/sign-up")
    public String saveBreeder(@ModelAttribute User user){
        String hash = passwordEncoder.encode(user.getPassword());
        user.setPassword(hash);
        usersdao.save(user);
        return "redirect:/login";
    }










}
