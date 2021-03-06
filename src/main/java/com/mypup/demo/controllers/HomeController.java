package com.mypup.demo.controllers;

import com.mypup.demo.models.User;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.Positive;

@Controller
public class HomeController {
    @GetMapping("/")
    public String landingPage() {
        return "landing";
    }

    @GetMapping("/index")
    public String welcome(Model model) {
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("showUserRoles", loggedIn);
        return "index";
    }

    @GetMapping("/home")
    public String homePage(Model model){
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("showUserRoles", loggedIn);
        return "breeder-posts/home";
    }

    @GetMapping("/about-MyPup")
    public String aboutPage(Model model){
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("showUserRoles", loggedIn);
        return "about-MyPup";
    }

    @GetMapping("/about")
    public String visitorAboutPage(){
        return "about";
    }

}