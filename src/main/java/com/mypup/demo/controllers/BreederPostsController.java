package com.mypup.demo.controllers;

import com.mypup.demo.repos.PostsRepo;
import com.mypup.demo.repos.UsersRepo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class BreederPostsController {

    private PostsRepo breederPostDao;
    private UsersRepo userDao;

    public BreederPostsController(PostsRepo breederPostDao, UsersRepo userDao) {
        this.breederPostDao = breederPostDao;
        this.UsersDao = userDao;
    }

    @GetMapping("/breeder-posts")
    public String getBreederPosts(Model model){
        model.addAttribute("breeder-posts", breederPostDao.findAll());
        return "breeder-posts/index";
    }


    @GetMapping ("/breeder-posts/{id}")
    public String getBreederPosts(@PathVariable long id, Model model){
        model.addAttribute("breeder-post", breederPostDao.getOne(id));
        return "breeder-post/show";
    }

    @GetMapping("/breeder-posts/create")
    public String getCreatedBreederPostForm(Model model){
        model.addAttribute("breeder-posts", new BreederPostsController());
        return "breeder-posts/create";
    }
    @PostMapping("/breeder-posts/create")
    public String createBreederPost(@ModelAttribute BreederPostsController breederPosts){
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
//        BreederPosts breederPostToEdit = breederPostDao.getOne(id);
        model.addAttribute("breeder-post", editBreederPostForm());
        return "breeder-posts/update";
    }


    @PostMapping("/breeder-posts/{id}/update")
    public String updateBreederPost(@PathVariable long id, @ModelAttribute BreederPostsController bp) {
        bp = breederPostDao.getOne(id);
        bp.setTitle(bp.getTitle());
        bp.setBody(bp.getBody());
        breederPostDao.save(bp);
        return "redirect:/breeder-post";
    }


}


