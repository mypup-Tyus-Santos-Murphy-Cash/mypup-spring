package com.mypup.demo.controllers;

import com.mypup.demo.models.User;
import com.mypup.demo.repos.DogPostRepo;
import com.mypup.demo.repos.FavoritesRepo;
import com.mypup.demo.repos.UserRepo;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;


@Controller
public class UsersController {
    private UserRepo usersdao;
    private PasswordEncoder passwordEncoder;
    private DogPostRepo dogPostDao;
    private FavoritesRepo favoritesDao;

    public UsersController(UserRepo users, PasswordEncoder passwordEncoder, DogPostRepo dogPostDao, FavoritesRepo favoritesDao) {
        this.usersdao = users;
        this.passwordEncoder = passwordEncoder;
        this.dogPostDao = dogPostDao;
        this.favoritesDao = favoritesDao;
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

    @GetMapping("/buyer-profile")
    public String gotToBuyer(Model model) {
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userRoleBuyer", loggedIn);
        if(loggedIn.getUserRole().equals("buyer"))
            return "users/buyer-profile";
        else
            return "redirect:/login";
    }

    @GetMapping("/breeder-profile")
    public String goToBreeder(Model model) {
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userRoleBreeders", loggedIn);
        model.addAttribute("breederPosts", usersdao.findUserById(loggedIn.getId()).getDogPost());
        if (loggedIn.getUserRole().equals("breeder"))
            return "users/breeder-profile";
        else
            return "redirect:/login";
    }

    @GetMapping("/admin-profile")
    public String goToAdmin(Model model) {
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("userRoleAdmin", loggedIn);
        model.addAttribute("allPosts",  dogPostDao.findAll());
        if(loggedIn.getUserRole().equals("admin"))
            return "users/admin-profile";
        else
            return "redirect:/login";
    }


    @GetMapping("/breeder-contact/{id}")
    public String goToBreederContactInfo(@PathVariable long id, Model model){
        model.addAttribute("breeder",usersdao.findUserByDogPost(dogPostDao.findById(id)));
        return "users/breeder-contact";
    }



}
