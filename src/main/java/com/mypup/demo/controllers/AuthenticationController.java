package com.mypup.demo.controllers;

import com.mypup.demo.models.User;
import com.mypup.demo.repos.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class AuthenticationController {
    private UserRepo userDao;

    public AuthenticationController(UserRepo userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "users/login";
    }


}
