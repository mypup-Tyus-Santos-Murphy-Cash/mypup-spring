package com.mypup.demo.controllers;

import com.mypup.demo.models.DogPost;
import com.mypup.demo.models.Favorites;
import com.mypup.demo.models.User;
import com.mypup.demo.repos.DogPostRepo;
import com.mypup.demo.repos.FavoritesRepo;
import com.mypup.demo.repos.UserRepo;
import com.mypup.demo.util.HibernateUtil;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.management.Query;
import java.util.List;


@Controller
public class BreederPostsController {

    private DogPostRepo dogPostDao;
    private UserRepo userDao;
    private FavoritesRepo favoritesDao;

    public BreederPostsController(DogPostRepo dogPostDao, UserRepo userDao, FavoritesRepo favoritesDao) {
        this.dogPostDao = dogPostDao;
        this.userDao = userDao;
        this.favoritesDao = favoritesDao;
    }

    @GetMapping("/breeder-posts")
    public String getBreederPosts(Model model){
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        session.beginTransaction();
        List<DogPost> dogPost = session.createQuery("SELECT dogPost FROM DogPost dogPost WHERE dogPost.id =:dog_post_id").list();
        User loggedIn = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        model.addAttribute("showUserRoles", loggedIn);
        model.addAttribute("breederPosts", dogPostDao.findAll());
        for(DogPost post : dogPost) {
            model.addAttribute("breederPosts2", dogPostDao.findById(post.getId()));
        }
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

//    @GetMapping ("/breeder-posts/{id}")
//    public String getBreederPosts(@PathVariable long id, Model model){
//        model.addAttribute("breederPosts2", dogPostDao.findDogPostsById(id));
//        return "breeder-posts/show";
//    }

    @PostMapping("/buyer-profile/{id}/addToFavorites")
    public String addToFavorites(@PathVariable long id, @RequestParam String dogBreed, @RequestParam String dogGroup, @RequestParam String dogDescription, @RequestParam String dogPrice, @RequestParam String images) {
        Favorites addToFavorites = favoritesDao.getOne(id);
        addToFavorites.setDogBreed(dogBreed);
        addToFavorites.setDogGroup(dogGroup);
        addToFavorites.setDogDescription(dogDescription);
        addToFavorites.setDogPrice(dogPrice);
        addToFavorites.setImages(images);
        favoritesDao.save(addToFavorites);
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









