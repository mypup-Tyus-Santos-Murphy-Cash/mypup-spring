package com.mypup.demo.controllers;

import com.mypup.demo.models;
import com.mypup.demo.models.DogPost;
import com.mypup.demo.repos.DogPostRepo;
import com.mypup.demo.repos.UserRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        model.addAttribute("breeder-posts", dogPostDao.findAll());
        return "breeder-posts/index";
    }


    @GetMapping ("/breeder-posts/{id}")
    public String getBreederPosts(@PathVariable long id, Model model){
        model.addAttribute("breeder-post", dogPostDao.getOne(id));
        return "breeder-post/show";
    }
//get these to work//
    @GetMapping("/breeder-posts/create")
    public String getCreatedBreederPostForm(Model model){
        model.addAttribute("breeder-post", new BreederPost());
        return "breeder-posts/create";
    }

    @PostMapping("/breeder-posts/create")
    public String createBreederPost(@ModelAttribute breederPosts breederPosts){
        breederPosts.setUser(userDao.getOne(1L));
        breederPostDao.save(breederPosts);
        return "redirect:/breeder-posts";
    }

    @PostMapping("/breeder-posts/{id}/delete")
    public String deleteBreederPost(@PathVariable long id){
        // delete post
        breederPostDao.deleteById(id);
        return "redirect:/breeder-posts";
    }
    @GetMapping("/breeder-posts/{id}/update")
    public String editBreederPostForm(@PathVariable long id, Model model) {
        BreederPosts breederPostToEdit = breederPostDao.getOne(id);
        model.addAttribute("breeder-post", editBreederPostForm(id, model));
        return "breeder-posts/update";
    }


    @PostMapping("/breeder-posts/{id}/update")
    public String updateBreederPost(@PathVariable long id, @ModelAttribute DogPost dogPost) {
        DogPost newDogPost = dogPostDao.getOne(id);
        newDogPost.setBreeds(newDogPost.getBreeds());
        newDogPost.setDogDescription(newDogPost.getDogDescription());
        newDogPost.setDogGroup(newDogPost.getDogGroup());
        newDogPost.setDogPrice(newDogPost.getDogPrice());
        dogPostDao.save(newDogPost);
        return "redirect:/breeder-post";
    }


}


