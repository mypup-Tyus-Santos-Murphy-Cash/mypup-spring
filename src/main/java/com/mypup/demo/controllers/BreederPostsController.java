package com.mypup.demo.controllers;

import com.mypup.demo.models.Breed;
import com.mypup.demo.models.DogPost;
import com.mypup.demo.models.User;
import com.mypup.demo.repos.DogPostRepo;
import com.mypup.demo.repos.UserRepo;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class BreederPostsController {

    private DogPostRepo dogPostDao;
    private UserRepo userDao;

    public BreederPostsController(DogPostRepo dogPostDao, UserRepo userDao) {
        this.dogPostDao = dogPostDao;
        this.userDao = userDao;
    }


    @GetMapping("/breeder-posts")
    public String getBreederPosts(Model model){
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("showUserRoles", loggedIn);
        model.addAttribute("breederPosts", dogPostDao.findAll());
        return "breeder-posts/show";
    }

    @GetMapping("/visitor-show")
    public String getVisitorShow(Model model) {
        model.addAttribute("breederPosts2", dogPostDao.findAll());
        return "breeder-posts/visitor-show";
    }

    @GetMapping ("/breeder-posts/{id}")
    public String getBreederPosts(@PathVariable long id, Model model){
        model.addAttribute("breederPosts", dogPostDao.getOne(id));
        return "breeder-posts/show";
    }

    @GetMapping("/breeder-posts/create")
    public String getCreatedBreederPostForm(Model model){
        model.addAttribute("newDogPost", new DogPost());
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("showUserRoles", loggedIn);
        if(loggedIn.getUserRole().equals("breeder")  || loggedIn.getUserRole().equals("admin"))
            return "breeder-posts/create";
        else
        return "redirect:/home";
    }

    @PostMapping("/breeder-posts/create")
    public String createBreederPost(@ModelAttribute DogPost newDogPost, @RequestParam String dogBreed, @RequestParam String dogGroup, @RequestParam String dogDescription, @RequestParam String dogPrice, @RequestParam String images){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newDogPost.setDogBreed(dogBreed);
        newDogPost.setDogGroup(dogGroup);
        newDogPost.setDogDescription(dogDescription);
        newDogPost.setDogPrice(dogPrice);
        newDogPost.setImages(images);
        newDogPost.setUser(loggedInUser);
        dogPostDao.save(newDogPost);
        return "redirect:/breeder-posts";
    }


    @PostMapping("/breeder-profile/{id}/delete")
    public String deletePost(@PathVariable long id){
            dogPostDao.deleteById(id);
        return "redirect:/breeder-profile";
    }

    @GetMapping("/breeder-profile/{id}/update")
    public String updateDogPostForm(@PathVariable long id, Model model) {
        model.addAttribute("breederPosts", dogPostDao.getOne(id));
        return "breeder-posts/update";
    }

    @PostMapping("/breeder-profile/{id}/update")
    public String updateBreederPost(@PathVariable long id, @RequestParam String dogBreed, @RequestParam String dogGroup, @RequestParam String dogDescription, @RequestParam String dogPrice, @RequestParam String images) {
        DogPost newDogPost = dogPostDao.getOne(id);
        newDogPost.setDogBreed(dogBreed);
        newDogPost.setDogGroup(dogGroup);
        newDogPost.setDogDescription(dogDescription);
        newDogPost.setDogPrice(dogPrice);
        newDogPost.setImages(images);
        dogPostDao.save(newDogPost);
        return "redirect:/breeder-profile";
    }

    @GetMapping("/companion-search")
    public String showCompanion(Model model) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("companion", loggedInUser);
        if(loggedInUser.getUserRole().equals("buyer"))
            return "companion-search";
        else
            return "redirect:/home";
    }

    @GetMapping("breeder-posts/favorites")
    public String favorites(@PathVariable long id, @ModelAttribute DogPost dogPost) {
        DogPost favoritePost = dogPostDao.findById(id);
        favoritePost.setUser(favoritePost.getUser());
        dogPostDao.save(favoritePost);
        return "users/buyer-profile";
    }



}


