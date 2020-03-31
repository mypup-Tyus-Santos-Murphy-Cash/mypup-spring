package com.mypup.demo.controllers;

//import com.mypup.demo.models;
//import com.mypup.demo.models.DogPost;
//import com.mypup.demo.models.User;
import com.mypup.demo.models.DogPost;
import com.mypup.demo.repos.DogPostRepo;
import com.mypup.demo.repos.UserRepo;
//import org.springframework.security.core.context.SecurityContextHolder;
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
        return "breeder-posts/index";
    }


    @GetMapping ("/breeder-posts/{id}")
    public String getBreederPosts(@PathVariable long id, Model model){
        model.addAttribute("breederPosts", dogPostDao.getOne(id));
        return "breeder-posts/show";
    }
//get these to work//
    @GetMapping("/breeder-posts/create")
    public String getCreatedBreederPostForm(Model model){
        model.addAttribute("dogPost", new DogPost());
        return "breeder-posts/create";
    }

    @PostMapping("/breeder-posts/create")
    public String createBreederPost(@ModelAttribute DogPost dogPost){
//        User loggedinUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (loggedinUser.getId() == dogPostDao.getOne(id).getUser().getId())
        dogPost.setUser(userDao.getOne(1L));
        dogPostDao.save(dogPost);
        return "redirect:/breeder-posts";
    }

    @DeleteMapping("/breeder-posts/{id}/delete")
    public String deletePost(@PathVariable long id){
//        User loggedinUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//        if (loggedinUser.getId() == dogPostDao.getOne(id).getUser().getId())
            dogPostDao.deleteById(id);
        return "redirect:/posts/index";
    }

    @GetMapping("/breeder-posts/{id}/update")
    public String editDogPostForm(@PathVariable long id, Model model) {
        model.addAttribute("breederPosts", editDogPostForm(id, model));
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

