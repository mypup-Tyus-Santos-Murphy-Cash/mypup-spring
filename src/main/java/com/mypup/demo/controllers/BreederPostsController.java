package com.mypup.demo.controllers;

//import com.mypup.demo.models;
//import com.mypup.demo.models.DogPost;
//import com.mypup.demo.models.User;
import com.mypup.demo.models.DogPost;
import com.mypup.demo.models.User;
import com.mypup.demo.repos.DogPostRepo;
import com.mypup.demo.repos.UserRepo;
//import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolder;
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
        model.addAttribute("breederPosts", dogPostDao.findAll());
        return "breeder-posts/show";
    }


    @GetMapping ("/breeder-posts/{id}")
    public String getBreederPosts(@PathVariable long id, Model model){
        model.addAttribute("breederPosts", dogPostDao.getOne(id));
        return "breeder-posts/show";
    }
//get these to work//
    @GetMapping("/breeder-posts/create")
    public String getCreatedBreederPostForm(Model model){
        model.addAttribute("newDogPost", new DogPost());
        User loggedIn = (User)
        SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(loggedIn != null)
            return "breeder-posts/create";
        else
        return "redirect:/login";
    }

    @PostMapping("/breeder-posts/create")
    public String createBreederPost(@ModelAttribute DogPost newDogPost, @RequestParam String dogBreed, @RequestParam String dogGroup, @RequestParam String dogDescription, @RequestParam String dogPrice){
        User loggedInUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        newDogPost.setDogBreed(dogBreed);
        newDogPost.setDogGroup(dogGroup);
        newDogPost.setDogDescription(dogDescription);
        newDogPost.setDogPrice(dogPrice);
        newDogPost.setUser(loggedInUser);
        dogPostDao.save(newDogPost);
        return "redirect:/breeder-posts";
    }

    @DeleteMapping("/breeder-posts/{id}/delete")
    public String deletePost(@PathVariable long id){
        User loggedinUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedinUser.getId() == dogPostDao.getOne(id).getUser().getId())
            dogPostDao.deleteById(id);
        return "redirect:/breeder-posts/home";
    }

    @GetMapping("/breeder-posts/{id}/update")
    public String updateDogPostForm(@PathVariable long id, Model model) {
        model.addAttribute("breederPosts", updateDogPostForm(id, model));
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
        return "redirect:/breeder-posts";
    }

    @GetMapping("breeder-posts/favorites")
    public String favorites(@PathVariable long id, @ModelAttribute DogPost dogPost) {
        DogPost favoritePost = dogPostDao.findById(id);
        favoritePost.setUser(favoritePost.getUser());
        dogPostDao.save(favoritePost);
        return "redirect:/users/buyer-profile";
    }



}


