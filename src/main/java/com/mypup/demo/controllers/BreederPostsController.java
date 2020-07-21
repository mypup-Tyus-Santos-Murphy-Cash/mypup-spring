package com.mypup.demo.controllers;

import com.mypup.demo.models.DogPost;
import com.mypup.demo.models.User;
import com.mypup.demo.repos.DogPostRepo;
import com.mypup.demo.repos.UserRepo;
//import com.mypup.demo.util.HibernateUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;


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

    //get these to work//
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
    public String createBreederPost(@ModelAttribute DogPost newDogPost, @RequestParam String dogBreed, @RequestParam String dogGroup, @RequestParam String dogDescription, @RequestParam String dogPrice, String images){
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

    @PostMapping("/admin-profile/{id}/delete")
    public String deletePostAdmin(@PathVariable long id){
        dogPostDao.deleteById(id);
        return "redirect:/admin-profile";
    }

    @GetMapping("/admin-profile/{id}/update2")
    public String updateDogPostFormAdmin(@PathVariable long id, Model model) {
        model.addAttribute("allPosts", dogPostDao.getOne(id));
        return "breeder-posts/update2";
    }

    @PostMapping("/admin-profile/{id}/update2")
    public String updateBreederPostAdmin(@PathVariable long id, @RequestParam String dogBreed, @RequestParam String dogGroup, @RequestParam String dogDescription, @RequestParam String dogPrice, @RequestParam String images) {
        DogPost newDogPost = dogPostDao.getOne(id);
        newDogPost.setDogBreed(dogBreed);
        newDogPost.setDogGroup(dogGroup);
        newDogPost.setDogDescription(dogDescription);
        newDogPost.setDogPrice(dogPrice);
        newDogPost.setImages(images);
        dogPostDao.save(newDogPost);
        return "redirect:/admin-profile";
    }

    @GetMapping("/favorites/{id}")
    public String favorites(Model model, @PathVariable long id) {
        model.addAttribute("breederPost", dogPostDao.getOne(id));
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("favorites", loggedInUser.getFavorites());
        return "users/buyer-profile";
    }

    @PostMapping("/favorites/{id}")
    public String addToFavorites(@PathVariable long id) {
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userDao.findUserById(loggedInUser.getId());
        DogPost dogPost = dogPostDao.findById(id);
        user.addFavorite(dogPost);
        userDao.save(user);
        dogPostDao.save(dogPost);
        return "redirect:/buyer-profile";
    }

    @PostMapping("/buyer-profile/{id}/remove-from-favorites")
    public String removeFromFavorites(@PathVariable long id){

        dogPostDao.deleteById(id);
        return "redirect:/buyer-profile";
    }

    @GetMapping("/companion-search")
    public String showCompanion(Model model){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("companion", loggedInUser);
        if (loggedInUser.getUserRole().equals("buyer"))
            return "companion-search";
        else
            return "redirect:/home";
    }

    @GetMapping(value = "/search/{searchTerm}")
    public String Search (Model model, @PathVariable String searchTerm){
        if (searchTerm.equals("")){
            return "redirect:breeder-posts";
        }else{
            User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            model.addAttribute("breederPosts", dogPostDao.findByDogBreed(searchTerm));
            model.addAttribute("showUserRoles",loggedInUser);
            return "breeder-posts/show";
        }
    }

    @GetMapping(value = "/visitor-search/{searchTerm}")
    public String visitorSearch (Model model, @PathVariable String searchTerm){
        if (searchTerm.equals("")){
            return "redirect:/visitor-show";
        }else{
            model.addAttribute("breederPosts2", dogPostDao.findByDogBreed(searchTerm));
            return "breeder-posts/visitor-show";
        }
    }


}
