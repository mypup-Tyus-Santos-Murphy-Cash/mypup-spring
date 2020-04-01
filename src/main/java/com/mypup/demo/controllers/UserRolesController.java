package com.mypup.demo.controllers;

import com.mypup.demo.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserRolesController {
    private UserRepo userDao;

    public UserRolesController(UserRepo userDao) {
        this.userDao = userDao;
    }

    @GetMapping("/users/breeder-profile")
        public String goToBreeder(Model model){
         model.addAttribute("breederPost", userDao.findByUserRole("breeder"));
         return "users/breeder-profile";
        }

    }



