package com.mypup.demo.controllers;

import com.mypup.demo.models.User;
import com.mypup.demo.repos.UserRepo;
import org.springframework.data.annotation.Id;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class AuthenticationController {
    private UserRepo userDao;

    public AuthenticationController(UserRepo userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/login")
    public String showLoginForm(Model model) {
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("showUserRole", loggedIn.getUserRole());
//        model.addAttribute("showBreederRole", userDao.findByUserRole("breeder"));
//        model.addAttribute("showBuyerProfile", userDao.findByUserRole("buyer"));
//        model.addAttribute("showAdminProfile", userDao.findByUserRole("admin"));
        return "users/login";
    }


}
